package com.eats.msmenu.meals.dto;

import java.math.BigDecimal;

public record UpdateMealRequest(
        String name,
        String description,
        BigDecimal price
) {
}
