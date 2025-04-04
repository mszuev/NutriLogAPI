package ru.mzuev.nutrilogapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mzuev.nutrilogapi.dto.MealRequest;
import ru.mzuev.nutrilogapi.dto.MealResponse;
import ru.mzuev.nutrilogapi.exception.ResourceNotFoundException;
import ru.mzuev.nutrilogapi.mapper.MealMapper;
import ru.mzuev.nutrilogapi.model.Meal;
import ru.mzuev.nutrilogapi.model.User;
import ru.mzuev.nutrilogapi.repository.MealRepository;
import ru.mzuev.nutrilogapi.repository.UserRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для операций с приемами пищи.
 */
@Service
@RequiredArgsConstructor
public class MealService {

    private final MealRepository mealRepository;
    private final UserRepository userRepository;
    private final MealMapper mealMapper;

    /**
     * Создает прием пищи.
     *
     * @param request DTO с данными для создания
     * @return DTO созданного приема пищи
     * @throws ResourceNotFoundException если пользователь не найден
     */
    @Transactional
    public MealResponse createMeal(MealRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));

        Meal meal = mealMapper.toEntity(request, user);
        return mealMapper.toResponse(mealRepository.save(meal));
    }

    /**
     * Получает приемы пищи пользователя за конкретную дату.
     *
     * @param userId ID пользователя
     * @param date Дата приема пищи
     * @return список DTO приемов пищи
     */
    @Transactional(readOnly = true)
    public List<MealResponse> getMealsByUserAndDate(Long userId, LocalDate date) {
        return mealRepository.findByUserIdAndDate(userId, date).stream()
                .map(mealMapper::toResponse)
                .collect(Collectors.toList());
    }
}