package com.example.restaurant.controller;

import com.example.restaurant.model.Restaurant;
import com.example.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1")
@CrossOrigin
public class RestaurantController {
    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/restaurants")
    public List<Restaurant> getAllRestaurants(){
        return restaurantService.getAllRestaurants();
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/restaurants/{restaurantId}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable(value = "restaurantId") UUID restaurantId){
        Restaurant restaurant = restaurantService.getRestaurant(restaurantId);

        if(restaurant == null)
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(restaurant,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @PostMapping("/restaurants")
    public ResponseEntity addRestaurant(@RequestBody final Restaurant restaurant){
        restaurantService.addRestaurant(restaurant);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/restaurants/{restaurantId}")
    public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable(value = "restaurantId")final UUID restaurantId)
    {
        Restaurant restaurant = restaurantService.deleteRestaurant(restaurantId);
        if (restaurant == null)
        {
            return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(restaurant,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @PutMapping("/restaurants/{restaurantId}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable(value = "restaurantId") UUID restaurantId, @RequestBody Restaurant restaurant)
    {
        if(restaurantService.getRestaurant(restaurantId)==null)
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(restaurantService.updateRestaurant(restaurantId,restaurant),HttpStatus.OK);
    }
    
}
