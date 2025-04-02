package ru.mzuev.nutrilogapi.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO для запроса на создание приема пищи.
 */
@Data
public class MealRequest {

    @NotNull(message = "{validation.meal.user_id.notnull}")
    private Long userId;

    @NotNull(message = "{validation.meal.date.notnull}")
    private LocalDate date;

    @NotEmpty(message = "{validation.meal.dishes.not_empty}")
    private List<MealDishRequest> dishes;

    /**
     * DTO для блюда в составе приема пищи.
     */
    @Data
    public static class MealDishRequest {

        @NotNull(message = "{validation.meal.dish_id.notnull}")
        private Long dishId;

        @Positive(message = "{validation.meal.portions.positive}")
        private Integer portions;
    }
}