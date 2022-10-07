package com.example.restaurant.repository;

import com.example.restaurant.model.Menu;
import org.springframework.data.repository.CrudRepository;

public interface MenuRepository extends CrudRepository<Menu,Integer> {

}
