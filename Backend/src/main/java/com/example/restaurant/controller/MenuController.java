package com.example.restaurant.controller;

import com.example.restaurant.service.MenuService;
import com.example.restaurant.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
public class MenuController {
    private final MenuService menuService;


    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/restaurants/{restaurantId}/menus")
    public List<Menu> getAllMenus(@PathVariable(value = "restaurantId")int restaurantId){
        return menuService.getAllMenus(restaurantId);
    }

    @GetMapping("/restaurants/{restaurantId}/menus/{menuId}")
    public Menu getMenu(@PathVariable(value = "menuId")int menuId){
        return menuService.getMenu(menuId);
    }

    @PostMapping("/restaurants/{restaurantId}/menus")
    public void addMenu(@RequestBody Menu menu,@PathVariable(value = "restaurantId")int restaurantId){
        menuService.addMenu(menu,restaurantId);
    }
    @DeleteMapping("/restaurants/{restaurantId}/menus/{menuId}")
    public void deleteMenu(@PathVariable(value = "menuId") final int menuId){
        menuService.deleteMenu(menuId);
    }
    @PutMapping("/restaurants/{restaurantId}/menus/{menuId}")
    public void updateMenu(@PathVariable(value = "menuId")final int menuId, @RequestParam(required = false) final String title){
        menuService.updateMenu(menuId,title);
    }
    /*

    @DeleteMapping("/restaurants/{restaurantId}")
    public void deleteRestaurant(@PathVariable(value = "restaurantId")int restaurantId)
    {
        restaurantService.deleteRestaurant(restaurantId);
    }

    @PutMapping("/restaurants/{restaurantId}")
    public void updateRestaurant(@PathVariable(value = "restaurantId")int restaurantId, @RequestParam(required = false) double rating)
    {
        restaurantService.updateRestaurant(restaurantId,rating);
    }*/
}
