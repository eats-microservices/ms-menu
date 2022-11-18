package com.eats.msmenu.meals;

import com.eats.msmenu.restaurants.Restaurant;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_meal")
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private Boolean isAvailable;
    @ManyToOne
    private Restaurant restaurant;

    @Deprecated
    public Meal() {}

    public Meal(String name, String description, BigDecimal price, Boolean isAvailable, Restaurant restaurant) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.isAvailable = isAvailable;
        this.restaurant = restaurant;
    }

    public Meal(Long id, String name, String description, BigDecimal price, Boolean isAvailable, Restaurant restaurant) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.isAvailable = isAvailable;
        this.restaurant = restaurant;
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

    public Restaurant getRestaurant() {
        return restaurant;
    }
}
