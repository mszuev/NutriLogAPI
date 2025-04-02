package ru.mzuev.nutrilogapi.mapper;

import ru.mzuev.nutrilogapi.dto.MealRequest;
import ru.mzuev.nutrilogapi.dto.MealResponse;
import ru.mzuev.nutrilogapi.exception.ResourceNotFoundException;
import ru.mzuev.nutrilogapi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mzuev.nutrilogapi.repository.DishRepository;

/**
 * Маппер для преобразования между сущностью Meal и DTO
 */
@Component
public class MealMapper {

    private final DishRepository dishRepository;

    @Autowired
    public MealMapper(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    /**
     * Конвертирует MealRequest в сущность Meal
     * @param request DTO запроса
     * @param user связанный пользователь
     * @return сущность Meal
     * @throws ResourceNotFoundException если блюдо не найдено
     */
    public Meal toEntity(MealRequest request, User user) {
        Meal meal = Meal.create(user, request.getDate());
        request.getDishes().forEach(dishRequest ->
                meal.getDishes().add(toMealDish(dishRequest, meal))
        );
        return meal;
    }


    private MealDish toMealDish(MealRequest.MealDishRequest request, Meal meal) {
        Dish dish = dishRepository.findById(request.getDishId())
                .orElseThrow(() -> new ResourceNotFoundException("Блюдо не найдено"));

        return MealDish.create(meal, dish, request.getPortions());
    }

    /**
     * Конвертирует сущность Meal в MealResponse
     * @param meal сущность Meal
     * @return DTO ответа
     */
    public MealResponse toResponse(Meal meal) {
        MealResponse response = new MealResponse();
        response.setId(meal.getId());
        response.setDate(meal.getDate());
        response.setTotalCalories(meal.getTotalCalories());

        meal.getDishes().forEach(mealDish ->
                response.getDishes().add(toMealDishResponse(mealDish))
        );

        return response;
    }

    private MealResponse.MealDishResponse toMealDishResponse(MealDish mealDish) {
        MealResponse.MealDishResponse dishResponse = new MealResponse.MealDishResponse();
        dishResponse.setDishName(mealDish.getDish().getName());
        dishResponse.setPortions(mealDish.getPortions());
        dishResponse.setCaloriesPerPortion(mealDish.getDish().getCaloriesPerServing());
        return dishResponse;
    }
}