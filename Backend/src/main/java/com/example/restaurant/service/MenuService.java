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
        List <Menu> menus = menuRepository.findMenusByRestaurantId(restaurantId);
        return menus;
    }

    public Menu getMenu(int menuId, int restaurantId){
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);

        if(restaurant == null){
            return null;
        }
            for (Menu menu: restaurant.getMenus()){
                if (menu.getId()==menuId)
                {
                    return menu;
                }
            }

            return null;
    }

    public Menu addMenu(Menu menu, int restaurantId)
    {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
        if(restaurant == null)
        {
            return null;
        }
        menu.setRestaurant(restaurant);
        menuRepository.save(menu);
        return menu;
    }


    public Menu deleteMenu(int menuId,int restaurantId) {

        if(!restaurantRepository.existsById(restaurantId))
        {
           return null;
        }

        boolean exists = menuRepository.existsById(menuId);
        if(!exists)
        {
            return null;
        }

        menuRepository.deleteById(menuId);
        return new Menu();
    }


    public Menu updateMenu(int menuId, Menu menu) {
        Menu menuOld = menuRepository.findById(menuId).orElse(null);
        if(!menuRepository.existsById(menuId))
        {
            return null;
        }
        menuOld.setTitle(menu.getTitle());
        menuOld.setStartDate(menu.getStartDate());
        menuOld.setEndDate(menu.getEndDate());
        menuOld.setDescription(menu.getDescription());
        menuRepository.save(menuOld);

        return menuOld;
    }
}
