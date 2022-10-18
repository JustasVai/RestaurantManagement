package com.example.restaurant.repository;

import com.example.restaurant.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MenuItemRepository extends JpaRepository<MenuItem, UUID> {
    List<MenuItem> findMenuItemsByMenuId(UUID menuId);

}
