package com.eats.msmenu.meals.dto;

import com.eats.msmenu.meals.Meal;
import com.eats.msmenu.restaurants.Restaurant;
import com.eats.msmenu.restaurants.dto.RestaurantResponse;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

public class MealResponse {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean isAvailable;
    private RestaurantResponse restaurant;

    public MealResponse(Long id, String name, String description, BigDecimal price, Boolean isAvailable, RestaurantResponse restaurant) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.isAvailable = isAvailable;
        this.restaurant = restaurant;
    }

    public MealResponse(Meal meal) {
        this.id = meal.getId();
        this.name = meal.getName();
        this.description = meal.getDescription();
        this.price = meal.getPrice();
        this.isAvailable = meal.getAvailable();
        this.restaurant = new RestaurantResponse(
                meal.getRestaurant().getName(),
                meal.getRestaurant().getCreatedAt(),
                meal.getRestaurant().getAddress(),
                meal.getRestaurant().getRate());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public RestaurantResponse getRestaurant() {
        return restaurant;
    }
}