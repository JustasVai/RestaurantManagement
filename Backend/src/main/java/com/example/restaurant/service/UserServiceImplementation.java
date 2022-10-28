//package com.example.restaurant.service;
//
//import com.example.restaurant.model.User;
//import com.example.restaurant.repository.UserRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserServiceImplementation implements UserDetailsService{
//
//    private final UserRepository userRepository;
//
//    public UserServiceImplementation(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username" + username));
//        return UserDetailsImplementation.build(user);
//    }
//
//}
