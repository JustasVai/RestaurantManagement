package com.example.restaurant.repository;

import com.example.restaurant.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MenuRepository extends JpaRepository<Menu, UUID> {
    List<Menu> findMenusByRestaurantId(UUID restaurantId);
}
