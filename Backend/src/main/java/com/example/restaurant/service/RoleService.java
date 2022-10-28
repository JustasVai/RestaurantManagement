package com.example.restaurant.service;

import com.example.restaurant.model.Role;
import com.example.restaurant.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role getByName(String  name){
        return roleRepository.findByRoleName(name);
    }

    public Role save(Role role){
        return roleRepository.save(role);
    }

    public Role createNewRole(Role role) {
        return roleRepository.save(role);
    }
}
