package com.example.restaurant.menu;

import com.example.restaurant.restaurant.Restaurant;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="menu")
public class Menu {
    @Id
    private int id;
    private Date startDate;
    private Date endDate;
    private String title;
    private String description;
    /*@OneToMany
   //@JoinColumn(name = "restaurant_id")
    //private Restaurant restaurant;

    //public Restaurant getRestaurant() {
    //    return restaurant;
    //}

    //public void setRestaurant(Restaurant restaurant) {
      /  this.restaurant = restaurant;
    }/
*/
    public Menu(int id, Date startDate, Date endDate, String title, String description) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
        //this.restaurant = new Restaurant(restaurantId,"","",0,"","");
    }

    public Menu() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
