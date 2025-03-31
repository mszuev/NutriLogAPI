package ru.mzuev.nutrilogapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mzuev.nutrilogapi.dto.DishRequest;
import ru.mzuev.nutrilogapi.dto.DishResponse;
import ru.mzuev.nutrilogapi.exception.ConflictException;
import ru.mzuev.nutrilogapi.mapper.DishMapper;
import ru.mzuev.nutrilogapi.model.Dish;
import ru.mzuev.nutrilogapi.repository.DishRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DishService {

    private final DishRepository dishRepository;
    private final DishMapper dishMapper;

    public DishResponse createDish(DishRequest request) {
        if (dishRepository.existsByName(request.getName())) {
            throw new ConflictException("Блюдо с названием " + request.getName() + " уже существует");
        }
        Dish dish = dishMapper.toEntity(request);
        return dishMapper.toResponse(dishRepository.save(dish));
    }

    public List<DishResponse> getAllDishes() {
        return dishRepository.findAll().stream()
                .map(dishMapper::toResponse)
                .collect(Collectors.toList());
    }
}