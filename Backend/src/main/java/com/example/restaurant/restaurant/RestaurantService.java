package com.example.restaurant.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurantRepository.findAll().forEach(restaurants::add);
        return  restaurants;
    }

    public Restaurant getRestaurant(int id){
        return restaurantRepository.findById(id).orElse(new Restaurant());
    }

    public void addRestaurant(Restaurant restaurant)
    {
        restaurantRepository.save(restaurant);
    }


    public void deleteRestaurant(int restaurantId) {
        boolean exists = restaurantRepository.existsById(restaurantId);
        if(!exists)
        {
            throw new IllegalStateException("Restaurant with id = " + restaurantId + " does not exists");
        }
        restaurantRepository.deleteById(restaurantId);
    }

    @Transactional
    public void updateRestaurant(int restaurantId, double rating) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new IllegalStateException("Restaurant with id = " + restaurantId + " does not exists."));

        if(rating > 0 && restaurant.getRating() != rating)
        {
            restaurant.setRating(rating);
        }
    }

}
