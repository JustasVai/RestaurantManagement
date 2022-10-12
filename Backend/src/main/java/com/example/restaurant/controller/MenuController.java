package com.example.restaurant.controller;

import com.example.restaurant.service.MenuService;
import com.example.restaurant.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/restaurants/{restaurantId}")
public class MenuController {
    private final MenuService menuService;


    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/menus")
    public ResponseEntity<List<Menu>> getAllMenus(@PathVariable(value = "restaurantId")final int restaurantId){
        List<Menu> menu = menuService.getAllMenus(restaurantId);
        if(menu.isEmpty())
        {
            return  new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(menu,HttpStatus.OK);
    }

    @GetMapping("/menus/{menuId}")
    public ResponseEntity<Menu> getMenu(@PathVariable(value = "menuId")final int menuId,@PathVariable(value = "restaurantId")final int restaurantId){
        Menu menu = menuService.getMenu(menuId,restaurantId);
        if(menu == null)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }

    @PostMapping("/menus")
    public ResponseEntity<String> addMenu(@RequestBody Menu menu,@PathVariable(value = "restaurantId")final int restaurantId){

        Menu menu1 = menuService.addMenu(menu,restaurantId);
        if(menu1==null)
        {
            return new ResponseEntity<>("Restaurant with id " + restaurantId + " does not exists",HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>("Menu with id " + menu1.getId() + " created",HttpStatus.CREATED);
    }

    @DeleteMapping("/menus/{menuId}")
    public ResponseEntity<String> deleteMenu(@PathVariable(value = "menuId") int menuId, @PathVariable(value="restaurantId") int restaurantId){
       Menu menu =  menuService.deleteMenu(menuId,restaurantId);
       if(menu == null)
       {
           return new ResponseEntity<>("",HttpStatus.NOT_FOUND);
       }
       return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }

    @PutMapping("/menus/{menuId}")
    public ResponseEntity<Menu> updateMenu(@PathVariable(value = "menuId")final int menuId, @RequestBody Menu menu){

        Menu newMenu = menuService.updateMenu(menuId,menu);

        if(newMenu == null)
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newMenu,HttpStatus.OK);
    }

}
