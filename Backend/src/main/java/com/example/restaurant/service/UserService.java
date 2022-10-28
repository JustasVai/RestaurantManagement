package com.example.restaurant.service;

import com.example.restaurant.dto.RegistrationDto;
import com.example.restaurant.model.Role;
import com.example.restaurant.model.User;
import com.example.restaurant.repository.RoleRepository;
import com.example.restaurant.repository.UserRepository;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class UserService implements UserDetailsService {


    private UserRepository userRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userRepository.findByUsername(username);
        if(user==null)
        {
            throw new UsernameNotFoundException("User not found");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }
    //    @Autowired
//    private PasswordEncoder passwordEncoder;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = getByUsername(username);
//
//        if (user == null) {
//            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
//        } else {
//            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//            user.getRoles().forEach(role -> {
//                authorities.add(new SimpleGrantedAuthority(role.getName().name()));
//            });
//
//            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
//        }
//    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAll() {
        List<User> restaurants = new ArrayList<>();
        userRepository.findAll().forEach(restaurants::add);
        return  restaurants;
    }

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void registerNewUser(RegistrationDto signUpRequest) {
        String hashedPassword = passwordEncoder.encode(signUpRequest.password());
        Set<Role> roles = new HashSet<>();

        Role userRole = roleRepository.findByRoleName("ROLE_USER");

        roles.add(userRole);
        User user = new User();
        user.setUsername(signUpRequest.username());
        user.setEmail(signUpRequest.email());
        user.setPassword(hashedPassword);
        user.setRoles(roles);
        userRepository.save(user);
    }

//    public void addRoleToUser(String username,String name)
//    {
//        User user = userRepository.findByUsername(username);
//        Role role = roleRepository.findByName(name);
//        user.addRole(role);
//    }
//    public User create(RegistrationDto request) {
//        List<Role> roles = new ArrayList<>();
//        try {
//            roles.add(roleService.getByName(ERole.ROLE_USER));
//        } catch (Exception ex) {}
//
//        return create(request, roles);
//    }
//
//    public User create(RegistrationDto request, List<Role> roles) {
//        User user = new User(request.email(), request.username(), passwordEncoder.encode(request.password()), roles);
//        save(user);
//        return user;
//    }

}
