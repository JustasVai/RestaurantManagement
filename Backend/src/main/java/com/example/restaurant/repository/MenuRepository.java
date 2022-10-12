package com.example.restaurant.repository;

import com.example.restaurant.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu,Integer> {
    List<Menu> findMenusByRestaurantId(int restaurantId);
}
