package com.example.restaurant.service;

import com.example.restaurant.model.Restaurant;
import com.example.restaurant.repository.RestaurantRepository;
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
        return restaurantRepository.findById(id).orElse(null);
    }

    public void addRestaurant(Restaurant restaurant)
    {
        restaurantRepository.save(restaurant);
    }


    public Restaurant deleteRestaurant(int restaurantId) {

        boolean exists = restaurantRepository.existsById(restaurantId);

        if(!exists)
        {
            return null;
        }

        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
        restaurantRepository.deleteById(restaurantId);
        return restaurant;
    }


    public Restaurant updateRestaurant(int restaurantId,Restaurant restaurant) {

        Restaurant restaurantOld = restaurantRepository.findById(restaurantId).orElse(null);
        restaurantOld.setAddress(restaurant.getAddress());
        restaurantOld.setName(restaurant.getName());
        restaurantOld.setRating(restaurant.getRating());
        restaurantOld.setPhoneNumber(restaurant.getPhoneNumber());
        restaurantOld.setDescription(restaurant.getDescription());
        restaurantRepository.save(restaurantOld);
        return restaurantOld;
        /*
        double rating = restaurant.getRating();

        if(rating > 0 && restaurant.getRating() != rating && rating <= 5) {
            restaurantOld.setRating(rating);
        }

        return restaurantOld;*/
    }

}
