package ru.mzuev.nutrilogapi.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class MealResponse {
    private Long id;
    private LocalDate date;
    private Integer totalCalories;
    private List<MealDishResponse> dishes = new ArrayList<>();

    @Data
    public static class MealDishResponse {
        private String dishName;
        private Integer portions;
        private Integer caloriesPerPortion;
    }
}