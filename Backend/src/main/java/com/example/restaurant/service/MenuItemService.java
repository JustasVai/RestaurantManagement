package com.example.restaurant.service;

import com.example.restaurant.model.Menu;
import com.example.restaurant.model.MenuItem;
import com.example.restaurant.model.Restaurant;
import com.example.restaurant.repository.MenuItemRepository;
import com.example.restaurant.repository.MenuRepository;
import com.example.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<MenuItem> getAllMenuItems(int restaurantId,int menuId)
    {
       List<MenuItem> menuItems = menuItemRepository.findMenuItemsByMenuId(menuId);

       return menuItems;
    }
    public MenuItem getOneMenuItem(int menuId,int menuItemId)
    {
        final MenuItem item = menuItemRepository.findById(menuItemId).orElse(null);
        return item;
    }
    public MenuItem addOneMenuItem(MenuItem item, int menuId)
    {
        Menu menu = menuRepository.findById(menuId).orElse(null);
        if(menu == null)
        {
            return null;
        }
        item.setMenu(menu);
        menuItemRepository.save(item);
        return item;
    }
    public MenuItem deleteMenuItem(int menuId,int restaurantId, int menuItemId) {

        if(!restaurantRepository.existsById(restaurantId))
        {
            return null;
        }

        boolean exists = menuRepository.existsById(menuId);
        if(!exists)
        {
            return null;
        }
        if(!menuItemRepository.existsById(menuItemId)){
            return null;
        }

        menuItemRepository.deleteById(menuItemId);

        return new MenuItem("Name");
    }

}
