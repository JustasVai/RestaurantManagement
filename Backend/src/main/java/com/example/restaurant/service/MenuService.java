package com.example.restaurant.service;

import com.example.restaurant.model.Menu;
import com.example.restaurant.model.Restaurant;
import com.example.restaurant.repository.MenuRepository;
import com.example.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Menu> getAllMenus(int restaurantId) {
        List<Menu> menus= new ArrayList<>();
        menuRepository.findAll().forEach(menus::add);
        return  menus;
    }

    public Menu getMenu(int id){
        return menuRepository.findById(id).orElse(new Menu());
    }

    public void addMenu(Menu menu, int restaurantId)
    {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(new Restaurant());
        menu.setRestaurant(restaurant);
        menuRepository.save(menu);
    }


    public void deleteMenu(int menuId) {
        boolean exists = menuRepository.existsById(menuId);
        if(!exists)
        {
            throw new IllegalStateException("Menu with id = " + menuId + " does not exists");
        }
        menuRepository.deleteById(menuId);
    }

    @Transactional
    public void updateMenu(int menuId, String title) {
        Menu menu = menuRepository.findById(menuId).orElseThrow(() -> new IllegalStateException("Menu with id = " + menuId + " does not exists."));

        if(title!= null && menu.getTitle() != title)
        {
            menu.setTitle(title);
        }
    }
}
