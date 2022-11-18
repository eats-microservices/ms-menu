package com.eats.msmenu.restaurants.dto;

import javax.persistence.Column;
import java.time.Instant;

public record RestaurantResponse(
        String name,
        Instant createdAt,
        String address,
        Double rate
) {

    public RestaurantResponse(String name, Instant createdAt, String address, Double rate) {
        this.name = name;
        this.createdAt = createdAt;
        this.address = address;
        this.rate = rate;
    }
}
