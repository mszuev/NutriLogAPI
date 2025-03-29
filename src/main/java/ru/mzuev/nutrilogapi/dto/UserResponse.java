package ru.mzuev.nutrilogapi.dto;

import lombok.Data;
import ru.mzuev.nutrilogapi.model.enums.TargetType;
import java.time.Instant;

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
    private Instant createdAt;
}