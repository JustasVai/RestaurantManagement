package com.example.restaurant.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getAllRestaurants(){
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/restaurants/{restaurantId}")
    public Restaurant getRestaurant(@PathVariable(value = "restaurantId")int restaurantId){
        return restaurantService.getRestaurant(restaurantId);
    }

    @PostMapping("/restaurants")
    public void addRestaurant(@RequestBody final Restaurant restaurant){
        restaurantService.addRestaurant(restaurant);
    }

    @DeleteMapping("/restaurants/{restaurantId}")
    public void deleteRestaurant(@PathVariable(value = "restaurantId")int restaurantId)
    {
        restaurantService.deleteRestaurant(restaurantId);
    }

    @PutMapping("/restaurants/{restaurantId}")
    public void updateRestaurant(@PathVariable(value = "restaurantId")int restaurantId, @RequestParam(required = false) double rating)
    {
        restaurantService.updateRestaurant(restaurantId,rating);
    }
    
}
