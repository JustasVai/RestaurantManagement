package com.example.restaurant.controller;

import com.example.restaurant.dto.RegistrationDto;
import com.example.restaurant.model.Role;
import com.example.restaurant.model.User;
import com.example.restaurant.repository.RoleRepository;
import com.example.restaurant.repository.UserRepository;
import com.example.restaurant.service.RoleService;
import com.example.restaurant.service.UserService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api")

public class UserController {

    private UserService userService;
    private RoleService roleService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;

    public UserController(UserService userService, RoleService roleService, UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {

        return ResponseEntity.ok().body(userService.getAll());


    }
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles() {
        return ResponseEntity.ok().body(roleRepository.findAll());
    }
    @PostMapping ("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.save(user));
    }
    @PostMapping("/role/save")
    public ResponseEntity<Role>saveRole(@RequestBody Role role)
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(roleService.save(role));
    }
//    @PostMapping ("/role/addtouser")
//    public ResponseEntity<?>addRoleToUser(@RequestBody RoleToUserForm roleToUserForm)
//    {
//        userService.addRoleToUser(roleToUserForm.getUsername(),roleToUserForm.getRoleName());
//        User user = userService.getByUsername(roleToUserForm.getUsername());
//        user.getRoles().forEach(role -> {
//            System.out.println(role.getName());
//        });
//        return ResponseEntity.ok().build();
//    }

    @PostMapping("/signup")
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
