package com.example.restaurant.service;

import com.example.restaurant.model.Menu;
import com.example.restaurant.model.Restaurant;
import com.example.restaurant.repository.MenuRepository;
import com.example.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Menu> getAllMenus(int restaurantId) {
        return menuRepository.findMenusByRestaurantId(restaurantId);
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

    @Transactional
    public Menu deleteMenu(int menuId,int restaurantId) {
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


    public Menu updateMenu(int menuId, Menu menu, int restaurantId) {
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
