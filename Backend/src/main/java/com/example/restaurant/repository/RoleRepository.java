package com.example.restaurant.repository;

import com.example.restaurant.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role,String> {
   Role findByRoleName(String name);
}
