package com.eats.msmenu.reviews;

import com.eats.msmenu.meals.Meal;
import com.eats.msmenu.restaurants.Restaurant;

import javax.persistence.*;

@Entity
@Table(name = "tb_review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String reviewText;
    @Column(nullable = false)
    private Double rate;
    @ManyToOne
    private Restaurant restaurant;
    @ManyToOne
    private Meal meal;

    @Deprecated
    public Review() {}

    public Review(String title, String reviewText, Double rate, Restaurant restaurant, Meal meal) {
        this.title = title;
        this.reviewText = reviewText;
        this.rate = rate;
        this.restaurant = restaurant;
        this.meal = meal;
    }

    public Review(Long id, String title, String reviewText, Double rate, Restaurant restaurant, Meal meal) {
        this.id = id;
        this.title = title;
        this.reviewText = reviewText;
        this.rate = rate;
        this.restaurant = restaurant;
        this.meal = meal;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getReviewText() {
        return reviewText;
    }

    public Double getRate() {
        return rate;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Meal getMeal() {
        return meal;
    }
}
