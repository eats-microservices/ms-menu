package com.eats.msmenu.restaurants.schedule;

import javax.persistence.*;

@Entity
@Table(name = "tb_schedule_restaurant")
public class ScheduleRestaurant {

    @EmbeddedId
    private ScheduleRestaurantId scheduleId;
    private Integer hourStart;
    private Integer minuteStart;
    private Integer hourEnd;
    private Integer minuteEnd;

    @Deprecated
    public ScheduleRestaurant() {}

    public ScheduleRestaurant(ScheduleRestaurantId scheduleId, Integer hourStart, Integer minuteStart, Integer hourEnd, Integer minuteEnd) {
        this.scheduleId = scheduleId;
        this.hourStart = hourStart;
        this.minuteStart = minuteStart;
        this.hourEnd = hourEnd;
        this.minuteEnd = minuteEnd;
    }

    public ScheduleRestaurant(Integer hourStart, Integer minuteStart, Integer hourEnd, Integer minuteEnd) {
        this.hourStart = hourStart;
        this.minuteStart = minuteStart;
        this.hourEnd = hourEnd;
        this.minuteEnd = minuteEnd;
    }
}
