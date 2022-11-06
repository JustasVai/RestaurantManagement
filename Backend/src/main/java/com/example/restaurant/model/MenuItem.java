package com.example.restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "menu_item")
public class MenuItem {
    public enum FoodCategory {
        DRINK,
        FOOD,
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, length = 16)
    private UUID id;

    @NotEmpty
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


    public MenuItem(){}

    public MenuItem(UUID id, String name, Double price, String recipe, String description, Menu menu) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.recipe = recipe;
        this.description = description;
        this.menu = menu;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", recipe='" + recipe + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return Objects.equals(id, menuItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
