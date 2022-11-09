package com.example.restaurant.controller;

import com.example.restaurant.service.MenuService;
import com.example.restaurant.model.Menu;
import com.example.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/restaurants/{restaurantId}")
@CrossOrigin
public class MenuController {
    private final MenuService menuService;
    private final RestaurantService restaurantService;

    @Autowired
    public MenuController(MenuService menuService, RestaurantService restaurantService) {
        this.menuService = menuService;
        this.restaurantService = restaurantService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/menus")
    public ResponseEntity<List<Menu>> getAllMenus(@PathVariable(value = "restaurantId")final UUID restaurantId){

        List<Menu> menu = menuService.getAllMenus(restaurantId);

        if(restaurantService.getRestaurant(restaurantId)==null)
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(menu,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/menus/{menuId}")
    public ResponseEntity<Menu> getMenu(@PathVariable(value = "menuId")final UUID menuId,@PathVariable(value = "restaurantId")final UUID restaurantId){
        Menu menu = menuService.getMenu(menuId,restaurantId);
        if(menu == null)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/menus")
    public ResponseEntity addMenu(@RequestBody Menu menu,@PathVariable(value = "restaurantId")final UUID restaurantId){

        Menu menu1 = menuService.addMenu(menu,restaurantId);

        if(menu1==null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/menus/{menuId}")
    public ResponseEntity<Menu> deleteMenu(@PathVariable(value = "menuId") UUID menuId, @PathVariable(value="restaurantId") UUID restaurantId){
       Menu menu =  menuService.deleteMenu(menuId,restaurantId);
       if(menu == null)
       {
           return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
       }
       return new ResponseEntity<>(menu,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @PutMapping("/menus/{menuId}")
    public ResponseEntity<Menu> updateMenu(@PathVariable(value = "menuId")final UUID menuId, @RequestBody Menu menu, @PathVariable(value = "restaurantId")final UUID restaurantId){

        Menu newMenu = menuService.updateMenu(menuId,menu,restaurantId);

        if(newMenu == null)
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(newMenu,HttpStatus.OK);
    }

}
