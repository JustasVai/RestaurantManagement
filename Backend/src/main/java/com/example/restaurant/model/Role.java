package com.example.restaurant.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "role")
public class Role {

    @Id
    private String roleName;


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}


