package ru.mzuev.nutrilogapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mzuev.nutrilogapi.dto.MealResponse;
import ru.mzuev.nutrilogapi.dto.ReportResponse;
import ru.mzuev.nutrilogapi.exception.ResourceNotFoundException;
import ru.mzuev.nutrilogapi.mapper.MealMapper;
import ru.mzuev.nutrilogapi.model.User;
import ru.mzuev.nutrilogapi.repository.MealRepository;
import ru.mzuev.nutrilogapi.repository.UserRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для генерации отчетов.
 */
@Service
@RequiredArgsConstructor
public class ReportService {
    private final UserRepository userRepository;
    private final MealRepository mealRepository;
    private final MealMapper mealMapper;

    /**
     * Генерирует ежедневный отчет по питанию.
     *
     * @param userId ID пользователя
     * @param date Дата отчета
     * @return DTO с отчетом
     * @throws ResourceNotFoundException если пользователь не найден
     */
    @Transactional(readOnly = true)
    public ReportResponse generateDailyReport(Long userId, LocalDate date) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));

        List<MealResponse> meals = mealRepository.findByUserIdAndDate(userId, date).stream()
                .map(mealMapper::toResponse)
                .collect(Collectors.toList());

        int totalCalories = meals.stream()
                .mapToInt(MealResponse::getTotalCalories)
                .sum();

        double dailyNorm = user.getDailyCalorieNorm();
        boolean isTargetAchieved = isTargetAchieved(user, totalCalories, dailyNorm);

        return ReportResponse.builder()
                .date(date)
                .totalCalories(totalCalories)
                .dailyNorm((int) dailyNorm)
                .isTargetAchieved(isTargetAchieved)
                .meals(meals)
                .build();
    }

    /**
     * Проверяет, достигнута ли дневная цель по калориям в зависимости от типа цели.
     *
     * @param user Пользователь
     * @param totalCalories Суммарное потребление калорий
     * @param dailyNorm Дневная норма
     * @return true если цель достигнута
     */
    // MAINTENANCE true, если отклонение от дневной нормы не более 10%
    private static boolean isTargetAchieved(User user, int totalCalories, double dailyNorm) {
        boolean isTargetAchieved;

        switch (user.getTargetType()) {
            case WEIGHT_LOSS -> isTargetAchieved = (totalCalories < dailyNorm);
            case MAINTENANCE -> {
                double lowerBound = dailyNorm * 0.9;
                double upperBound = dailyNorm * 1.1;
                isTargetAchieved = (totalCalories >= lowerBound && totalCalories <= upperBound);
            }
            case WEIGHT_GAIN -> isTargetAchieved = (totalCalories > dailyNorm);
            default -> isTargetAchieved = false;
        }
        return isTargetAchieved;
    }
}