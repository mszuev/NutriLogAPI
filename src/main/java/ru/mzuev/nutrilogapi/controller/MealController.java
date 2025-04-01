package ru.mzuev.nutrilogapi.controller;

import ru.mzuev.nutrilogapi.dto.MealRequest;
import ru.mzuev.nutrilogapi.dto.MealResponse;
import ru.mzuev.nutrilogapi.service.MealService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/meals")
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MealResponse createMeal(@Valid @RequestBody MealRequest request) {
        return mealService.createMeal(request);
    }

    @GetMapping("/user/{userId}")
    public List<MealResponse> getMealsByUserAndDate(
            @PathVariable Long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return mealService.getMealsByUserAndDate(userId, date);
    }
}