package ru.mzuev.nutrilogapi.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO для ответа с информацией о приеме пищи.
 */
@Data
public class MealResponse {
    private Long id;
    private LocalDate date;
    private Integer totalCalories;
    private List<MealDishResponse> dishes = new ArrayList<>();

    /**
     * DTO для блюда в составе приема пищи.
     */
    @Data
    public static class MealDishResponse {
        private String dishName;
        private Integer portions;
        private Integer caloriesPerPortion;
    }
}