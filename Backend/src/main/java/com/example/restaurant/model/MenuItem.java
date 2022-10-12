package com.example.restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "menu_item")
public class MenuItem {
    public enum FoodCategory {
        DRINK,
        FOOD,
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Double price;
    private String recipe;
    private String description;
   // @Enumerated(EnumType.STRING)
    //@Column(name="food_category")
   // private FoodCategory category;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "menu_id",referencedColumnName = "id")
    private Menu menu;

    public MenuItem(String name) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.recipe = recipe;
        this.description = description;
       // this.category = category;
        this.menu = menu;
    }

    public MenuItem(){}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
