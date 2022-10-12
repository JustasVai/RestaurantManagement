package com.example.restaurant.controller;

import com.example.restaurant.model.Menu;
import com.example.restaurant.model.MenuItem;
import com.example.restaurant.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/restaurants/{restaurantId}/menus/{menuId}")
public class MenuItemController {

    private final MenuItemService menuItemService;

    @Autowired
    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @GetMapping("/items")
    public ResponseEntity<List<MenuItem>> getAllMenuItems(@PathVariable(value = "restaurantId")final int restaurantId,@PathVariable(value = "menuId")final int menuId){
        List<MenuItem> menuItems = menuItemService.getAllMenuItems(restaurantId,menuId);
        if(menuItems.isEmpty())
        {
            return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(menuItems,HttpStatus.OK);
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<MenuItem> getOneMenuItem(@PathVariable(value = "menuId")final int menuId,@PathVariable(value = "restaurantId")final int restaurantId,@PathVariable(value = "id")final int id){
        MenuItem menuItem = menuItemService.getOneMenuItem(menuId,id);
        if(menuItem == null)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(menuItem, HttpStatus.OK);
    }

    @PostMapping("/items")
    public ResponseEntity<String> addMenu(@RequestBody MenuItem menuItem,@PathVariable(value = "restaurantId")final int restaurantId,@PathVariable(value = "menuId")final int menuId){

        MenuItem menu1 = menuItemService.addOneMenuItem(menuItem,menuId);
        if(menu1==null)
        {
            return new ResponseEntity<>("Menu with id " + restaurantId + " does not exists",HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>("Menu item with id " + menu1.getId() + " created",HttpStatus.CREATED);
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<String> deleteMenu(@PathVariable(value = "menuId") int menuId, @PathVariable(value="restaurantId") int restaurantId,@PathVariable(value="itemId") int itemId){
        MenuItem menuItem = menuItemService.deleteMenuItem(menuId,restaurantId,itemId);
        if(menuItem == null)
        {
            return new ResponseEntity<>("",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Deleted" ,HttpStatus.OK);
    }
}
