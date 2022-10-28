//package com.example.restaurant.controller;
//
//import com.example.restaurant.dto.LoginDto;
//import com.example.restaurant.model.User;
//import com.example.restaurant.security.JwtTokenUtil;
//import com.example.restaurant.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/auth")
//@RequiredArgsConstructor
//public class AuthController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;
//
//    @PostMapping("/login")
//    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDto request) {
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.username(), request.password());
//        Authentication authentication = authenticationManager.authenticate(token);
//
//        org.springframework.security.core.userdetails.User userDetails = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
//        User user = userService.getByUsername(userDetails.getUsername());
//
//        String accessToken = jwtTokenUtil.generateAccessToken(user);
//        String refreshToken = jwtTokenUtil.generateRefreshToken(user);
//
//        Map<String, String> tokens = new HashMap<>();
//        tokens.put("access_token", accessToken);
//        tokens.put("refresh_token", refreshToken);
//
//        return ResponseEntity.ok().body(tokens);
//    }
//
////    @PostMapping("/signin")
////    public ResponseEntity<?> signin(@RequestBody SignInDto signInRequest) {
////        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
////        SecurityContextHolder.getContext().setAuthentication(authentication);
////        String jwt = jwtUtil.generateJwtToken(authentication);
////        UserDetailsImplementation userDetails = (UserDetailsImplementation) authentication.getPrincipal();
////        List<String> roles = userDetails.getAuthorities().stream()
////                .map(item -> item.getAuthority())
////                .collect(Collectors.toList());
////        JwtResponse res = new JwtResponse();
////        res.setToken(jwt);
////        res.setId(userDetails.getId());
////        res.setUsername(userDetails.getUsername());
////        res.setRoles(roles);
////        return ResponseEntity.ok(res);
////    }
////
////
//
//
//
//}
