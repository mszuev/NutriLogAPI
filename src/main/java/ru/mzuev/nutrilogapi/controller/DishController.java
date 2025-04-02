package ru.mzuev.nutrilogapi.controller;

import ru.mzuev.nutrilogapi.dto.DishRequest;
import ru.mzuev.nutrilogapi.dto.DishResponse;
import ru.mzuev.nutrilogapi.service.DishService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Контроллер для управления операциями с блюдами.
 */
@RestController
@RequestMapping("/api/v1/dishes")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    /**
     * Создает новое блюдо.
     *
     * @param request DTO с данными для создания блюда
     * @return созданное блюдо
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DishResponse createDish(@Valid @RequestBody DishRequest request) {
        return dishService.createDish(request);
    }

    /**
     * Получает список всех блюд.
     *
     * @return список DTO с информацией о блюдах
     */
    @GetMapping
    public List<DishResponse> getAllDishes() {
        return dishService.getAllDishes();
    }
}