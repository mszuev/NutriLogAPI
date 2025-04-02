package ru.mzuev.nutrilogapi.dto;

import lombok.Data;
import lombok.Builder;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO для ответа с ежедневным отчетом.
 */
@Data
@Builder
public class ReportResponse {
    private LocalDate date;
    private Integer totalCalories;
    private Integer dailyNorm;
    private Boolean isTargetAchieved;
    private List<MealResponse> meals;
}