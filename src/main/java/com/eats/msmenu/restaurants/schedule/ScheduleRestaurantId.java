package com.eats.msmenu.restaurants.schedule;

import com.eats.msmenu.restaurants.Restaurant;
import com.eats.msmenu.restaurants.schedule.DaysOfWeek;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class ScheduleRestaurantId implements Serializable {

    private Long restaurantId;
    @Enumerated(EnumType.STRING)
    private DaysOfWeek day;

    public ScheduleRestaurantId(Long restaurantId, DaysOfWeek day) {
        this.restaurantId = restaurantId;
        this.day = day;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public DaysOfWeek getDay() {
        return day;
    }
}
