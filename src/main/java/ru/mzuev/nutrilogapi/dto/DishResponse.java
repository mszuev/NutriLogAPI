package ru.mzuev.nutrilogapi.dto;

import lombok.Data;
import java.time.Instant;

@Data
public class DishResponse {
    private Long id;
    private String name;
    private Integer caloriesPerServing;
    private Double proteins;
    private Double fats;
    private Double carbohydrates;
}