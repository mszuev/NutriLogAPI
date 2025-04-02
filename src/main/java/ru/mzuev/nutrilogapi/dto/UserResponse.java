package ru.mzuev.nutrilogapi.dto;

import lombok.Data;
import ru.mzuev.nutrilogapi.model.enums.TargetType;

/**
 * DTO для ответа с данными пользователя.
 */
@Data
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private Double weight;
    private Double height;
    private TargetType targetType;
    private Double dailyCalorieNorm;
}