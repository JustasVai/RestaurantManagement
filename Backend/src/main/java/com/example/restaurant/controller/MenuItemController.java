package com.example.restaurant.controller;

import com.example.restaurant.model.Menu;
import com.example.restaurant.model.MenuItem;
import com.example.restaurant.service.MenuItemService;
import com.example.restaurant.service.MenuService;
import com.example.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/restaurants/{restaurantId}/menus/{menuId}")
public class MenuItemController {

    private final MenuItemService menuItemService;
    private final MenuService menuService;
    private final RestaurantService restaurantService;
    @Autowired
    public MenuItemController(MenuItemService menuItemService, MenuService menuService, RestaurantService restaurantService) {
        this.menuItemService = menuItemService;
        this.menuService = menuService;
        this.restaurantService = restaurantService;
    }

    @GetMapping("/items")
    public ResponseEntity<List<MenuItem>> getAllMenuItems(@PathVariable(value = "restaurantId")final UUID restaurantId, @PathVariable(value = "menuId")final UUID menuId){
        List<MenuItem> menuItems = menuItemService.getAllMenuItems(restaurantId,menuId);
        Menu menu = menuService.getMenu(menuId,restaurantId);
        if(!restaurantService.getRestaurant(restaurantId).getMenus().contains(menu) || menuItems.isEmpty())
        {
            return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(menuItems,HttpStatus.OK);
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<MenuItem> getOneMenuItem(@PathVariable(value = "menuId")final UUID menuId,@PathVariable(value = "restaurantId")final UUID restaurantId,@PathVariable(value = "id")final UUID id){
        MenuItem menuItem = menuItemService.getOneMenuItem(menuId,id, restaurantId);

        if(menuItem == null)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(menuItem, HttpStatus.OK);
    }

    @PostMapping("/items")
    public ResponseEntity addMenuItem(@RequestBody MenuItem menuItem,@PathVariable(value = "restaurantId")final UUID restaurantId,@PathVariable(value = "menuId")final UUID menuId){

        MenuItem menu1 = menuItemService.addOneMenuItem(menuItem,menuId,restaurantId);
        if(menu1==null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<MenuItem> deleteMenuItem(@PathVariable(value = "menuId") UUID menuId, @PathVariable(value="restaurantId") UUID restaurantId,@PathVariable(value="itemId") UUID itemId){
        MenuItem menuItem = menuItemService.deleteMenuItem(menuId,restaurantId,itemId);

        if(menuItem == null)
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(menuItem,HttpStatus.OK);
    }
    @PutMapping("/items/{itemId}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable(value = "menuId") UUID menuId, @PathVariable(value="restaurantId") UUID restaurantId,@PathVariable(value="itemId") UUID itemId, @RequestBody MenuItem menuItem)
    {
        MenuItem item = menuItemService.updateMenuItem(restaurantId,menuId,itemId,menuItem);

        if(item==null)
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(item,HttpStatus.OK);
    }

}
