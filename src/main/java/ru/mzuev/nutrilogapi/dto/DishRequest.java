package ru.mzuev.nutrilogapi.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * DTO для запроса на создание блюда.
 */
@Data
public class DishRequest {

    @NotBlank(message = "{validation.dish.name.blank}")
    private String name;

    @Positive(message = "{validation.dish.calories.positive}")
    private Integer caloriesPerServing;

    @PositiveOrZero(message = "{validation.dish.proteins.positive}")
    private Double proteins;

    @PositiveOrZero(message = "{validation.dish.fats.positive}")
    private Double fats;

    @PositiveOrZero(message = "{validation.dish.carbs.positive}")
    private Double carbohydrates;
}