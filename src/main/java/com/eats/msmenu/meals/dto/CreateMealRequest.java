package com.eats.msmenu.meals.dto;

import com.eats.msmenu.exceptions.ResourceNotFoundException;
import com.eats.msmenu.meals.Meal;
import com.eats.msmenu.restaurants.Restaurant;
import com.eats.msmenu.restaurants.RestaurantRepository;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

public record CreateMealRequest(
        String name,
        String description,
        BigDecimal price,
        Boolean isAvailable,
        Long restaurantId
) {

    public Meal toModel(RestaurantRepository restaurantRepository) {
        Restaurant restaurant = restaurantRepository
                .findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant with id " + restaurantId + " not found"));

        return new Meal(name, description, price, isAvailable, restaurant);
    }
}
