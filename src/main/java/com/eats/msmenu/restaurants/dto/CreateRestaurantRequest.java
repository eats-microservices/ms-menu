package com.eats.msmenu.restaurants.dto;

import com.eats.msmenu.restaurants.Restaurant;

import java.time.Instant;

public record CreateRestaurantRequest(
        String name,
        String address
) {
    public Restaurant toModel() {
        return new Restaurant(name(), Instant.now(), Instant.now(), address(),null);
    }
}
