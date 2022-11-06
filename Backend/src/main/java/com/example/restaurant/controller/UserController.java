package com.example.restaurant.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.restaurant.dto.RegistrationDto;
import com.example.restaurant.model.Role;
import com.example.restaurant.model.User;
import com.example.restaurant.repository.RoleRepository;
import com.example.restaurant.repository.UserRepository;
import com.example.restaurant.service.RoleService;
import com.example.restaurant.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api")

public class UserController {

    private UserService userService;
    private RoleService roleService;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private AuthenticationManager authenticationManager;

    public UserController(UserService userService, RoleService roleService, UserRepository userRepository, RoleRepository roleRepository,AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
    }
    @PreAuthorize("@securityService.hasUser(#id)")
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        User user = userRepository.findById(id).orElse(null);

        if(user==null)
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(user);
    }


    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getAll());
    }



    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles() {
        return ResponseEntity.ok().body(roleRepository.findAll());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/role/save")
    public ResponseEntity<Role>saveRole(@RequestBody Role role)
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(roleService.save(role));
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping ("/role/addToUser")
    public ResponseEntity<?>addRoleToUser(@RequestBody RoleToUser roleToUserForm)
    {
        Role role = roleRepository.findByRoleName(roleToUserForm.getRoleName());
        User user = userRepository.findByUsername(roleToUserForm.getUsername());
        if(role==null||user == null)
        {
            return ResponseEntity.notFound().build();
        }
        userService.addRoleToUser(roleToUserForm.getUsername(),roleToUserForm.getRoleName());
        return ResponseEntity.ok().build();
    }
    @GetMapping ("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try{
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = userService.getByUsername(username);

                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis()+1*60*1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles",user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String,String> tokens = new HashMap<>();
                tokens.put("access_token",access_token);
                tokens.put("refresh_token",refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),tokens);

            }catch (Exception exception)
            {
                response.setHeader("error",exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String,String> error = new HashMap<>();
                error.put("error_message",exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),error);
            }}
            else{
                throw new RuntimeException("Refresh token is missing");
            }
    }
    @PostMapping("/register")
    public ResponseEntity<String> signup(@RequestBody RegistrationDto signUpRequest) {

        if (userRepository.existsByUsername(signUpRequest.username())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("username is already taken");
        }

        if (userRepository.existsByEmail(signUpRequest.email())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("email is already taken");
        }
        userService.registerNewUser(signUpRequest);


        return ResponseEntity.ok("User registered success");
    }

    @PostMapping({"/createNewRole"})
    public Role createNewRole(@RequestBody Role role) {
        return roleService.createNewRole(role);
    }
}
@Data
class RoleToUser{
    private String username;
    private String roleName;
}
@Service("securityService")
class SecurityService {

    @Autowired
    UserService userService;
    Logger logger = LoggerFactory.getLogger(SecurityService.class);
    Authentication authentication;

    public boolean hasUser(String id){

        User user =  this.userService.getByUsername(id);
        this.authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName().equals(user.getUsername());
    }
}