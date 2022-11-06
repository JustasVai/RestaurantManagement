package com.example.restaurant.service;

import com.example.restaurant.model.Menu;
import com.example.restaurant.model.Restaurant;
import com.example.restaurant.repository.MenuRepository;
import com.example.restaurant.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Menu> getAllMenus(UUID restaurantId) {
        return menuRepository.findMenusByRestaurantId(restaurantId);
    }

    public Menu getMenu(UUID menuId, UUID restaurantId){
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);

        if(restaurant == null){
            return null;
        }
        for (Menu menu: restaurant.getMenus()){
                if (menu.getId().equals(menuId))
                {
                    log.info("{}",menu);
                    return menu;
                }
            }

            return null;
    }

    public Menu addMenu(Menu menu, UUID restaurantId)
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

    @Transactional
    public Menu deleteMenu(UUID menuId,UUID restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
        Menu menu = menuRepository.findById(menuId).orElse(null);

        if(restaurant == null || menu == null)
        {
           return null;
        }


        if(!restaurant.getMenus().contains(menu))
        {
            return null;
        }
        restaurant.getMenus().remove(menu);
        restaurantRepository.save(restaurant);
        menuRepository.delete(menu);

        return menu;
    }


    public Menu updateMenu(UUID menuId, Menu menu, UUID restaurantId) {
        Menu menuOld = menuRepository.findById(menuId).orElse(null);
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
        if(!menuRepository.existsById(menuId) || !restaurant.getMenus().contains(menuOld))
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
