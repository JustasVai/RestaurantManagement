package com.example.restaurant.menu;

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

    @PostMapping("/restaurants/{restaurantId}/menus/{menuId}")
    public void addRestaurant(@RequestBody Menu menu){
        //menu.set
        menuService.addMenu(menu);
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
