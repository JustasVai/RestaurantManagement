package com.example.restaurant.restaurant;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="restaurant")
public class Restaurant {
    @Id
    private int id;
    private String name;
    private String address;
    private double rating;
    private String phoneNumber;
    private String description;

    public Restaurant(int id, String name, String address, double rating, String phoneNumber, String description) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.phoneNumber = phoneNumber;
        this.description = description;
    }

    public Restaurant() {
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
