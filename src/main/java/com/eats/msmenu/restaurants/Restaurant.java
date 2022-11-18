package com.eats.msmenu.restaurants;

import com.eats.msmenu.meals.Meal;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "tb_restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Instant createdAt;
    @Column(nullable = false)
    private Instant updatedAt;
    @Column(nullable = false)
    private String address;
    private Double rate;
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.REMOVE)
    List<Meal> meals;

    @Deprecated
    public Restaurant() {}

    public Restaurant(String name, Instant createdAt, Instant updatedAt, String address, Double rate) {
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.address = address;
        this.rate = rate;
    }

    public Restaurant(Long id, String name, Instant createdAt, Instant updatedAt, String address, Double rate) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.address = address;
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public String getAddress() {
        return address;
    }

    public Double getRate() {
        return rate;
    }
}
