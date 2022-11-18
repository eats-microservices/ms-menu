package com.eats.msmenu.meals.dto;

import com.eats.msmenu.restaurants.Restaurant;
import com.eats.msmenu.restaurants.dto.RestaurantResponse;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

public record MealResponse(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Boolean isAvailable,
        RestaurantResponse restaurant
) {
}
