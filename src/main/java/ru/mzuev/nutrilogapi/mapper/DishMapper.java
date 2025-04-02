package ru.mzuev.nutrilogapi.mapper;

import ru.mzuev.nutrilogapi.dto.DishRequest;
import ru.mzuev.nutrilogapi.dto.DishResponse;
import ru.mzuev.nutrilogapi.model.Dish;
import org.springframework.stereotype.Component;

/**
 * Маппер для преобразования между сущностью Dish и DTO
 */
@Component
public class DishMapper {

    /**
     * Конвертирует DishRequest в сущность Dish
     * @param request DTO запроса
     * @return сущность Dish
     */
    public Dish toEntity(DishRequest request) {
        return Dish.create(
                request.getName(),
                request.getCaloriesPerServing(),
                request.getProteins(),
                request.getFats(),
                request.getCarbohydrates()
        );
    }

    /**
     * Конвертирует сущность Dish в DishResponse
     * @param dish сущность Dish
     * @return DTO ответа
     */
    public DishResponse toResponse(Dish dish) {
        DishResponse response = new DishResponse();
        response.setId(dish.getId());
        response.setName(dish.getName());
        response.setCaloriesPerServing(dish.getCaloriesPerServing());
        response.setProteins(dish.getProteins());
        response.setFats(dish.getFats());
        response.setCarbohydrates(dish.getCarbohydrates());
        return response;
    }
}