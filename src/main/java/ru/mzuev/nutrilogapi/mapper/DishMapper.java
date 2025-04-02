package ru.mzuev.nutrilogapi.mapper;

import ru.mzuev.nutrilogapi.dto.DishRequest;
import ru.mzuev.nutrilogapi.dto.DishResponse;
import ru.mzuev.nutrilogapi.model.Dish;
import org.springframework.stereotype.Component;

@Component
public class DishMapper {

    public Dish toEntity(DishRequest request) {
        return Dish.create(
                request.getName(),
                request.getCaloriesPerServing(),
                request.getProteins(),
                request.getFats(),
                request.getCarbohydrates()
        );
    }

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