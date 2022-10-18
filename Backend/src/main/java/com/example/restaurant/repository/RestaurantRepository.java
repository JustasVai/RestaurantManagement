package com.example.restaurant.repository;

import com.example.restaurant.model.Restaurant;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, UUID> {
}