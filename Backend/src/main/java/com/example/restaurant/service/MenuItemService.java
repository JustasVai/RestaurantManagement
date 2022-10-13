package com.example.restaurant.service;

import com.example.restaurant.model.Menu;
import com.example.restaurant.model.MenuItem;
import com.example.restaurant.model.Restaurant;
import com.example.restaurant.repository.MenuItemRepository;
import com.example.restaurant.repository.MenuRepository;
import com.example.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);

        List<MenuItem> menuItems= new ArrayList<>();

        Menu menu = menuRepository.findById(menuId).orElse(null);

        if(restaurant==null || menu == null)
        {
            return menuItems;
        }

        menuItems = menuItemRepository.findMenuItemsByMenuId(menuId);

        return menuItems;
    }
    public MenuItem getOneMenuItem(int menuId, int menuItemId, int restaurantId)
    {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);

        Menu menu = menuRepository.findById(menuId).orElse(null);

        if(restaurant==null || menu == null)
        {
            return null;
        }

        final MenuItem item = menuItemRepository.findById(menuItemId).orElse(null);
        if(restaurant.getMenus().contains(menu) && menu.getItems().contains(item))
        {
            return item;
        }
        return null;
    }
    public MenuItem addOneMenuItem(MenuItem item, int menuId, int restaurantId)
    {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
        Menu menu = menuRepository.findById(menuId).orElse(null);

        if(menu == null || restaurant == null)
        {
            return null;
        }
        if (restaurant.getMenus().contains(menu))
        {
            item.setMenu(menu);
            menuItemRepository.save(item);
            return item;
        }

        return null;

    }
    @Transactional
    public MenuItem deleteMenuItem(int menuId,int restaurantId, int menuItemId) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
        Menu menu = menuRepository.findById(menuId).orElse(null);
        MenuItem menuItem = menuItemRepository.findById(menuItemId).orElse(null);

        if(restaurant == null || menuItem == null || menu == null)
        {
            return null;
        }

        if(!restaurant.getMenus().contains(menu) || !menu.getItems().contains(menuItem))
        {
            return null;
        }


        menu.getItems().remove(menuItem);
        menuRepository.save(menu);
        menuItemRepository.delete(menuItem);

        return menuItem;
    }

    public MenuItem updateMenuItem(int restaurantId, int menuId, int itemId, MenuItem menuItem) {
        Menu menu = menuRepository.findById(menuId).orElse(null);
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
        MenuItem oldItem = menuItemRepository.findById(itemId).orElse(null);

        if(restaurant.getMenus().contains(menu)&&menu.getItems().contains(oldItem))
        {
            oldItem.setDescription(menuItem.getDescription());
            oldItem.setPrice(menuItem.getPrice());
            oldItem.setRecipe(menuItem.getRecipe());
            oldItem.setName(menuItem.getName());
            menuItemRepository.save(oldItem);
            return  oldItem;
        }
        return null;

    }
}
