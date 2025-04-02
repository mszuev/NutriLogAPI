package ru.mzuev.nutrilogapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mzuev.nutrilogapi.dto.DishRequest;
import ru.mzuev.nutrilogapi.dto.DishResponse;
import ru.mzuev.nutrilogapi.exception.ConflictException;
import ru.mzuev.nutrilogapi.mapper.DishMapper;
import ru.mzuev.nutrilogapi.model.Dish;
import ru.mzuev.nutrilogapi.repository.DishRepository;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для операций с блюдами.
 */
@Service
@RequiredArgsConstructor
public class DishService {

    private final DishRepository dishRepository;
    private final DishMapper dishMapper;

    /**
     * Создает новое блюдо.
     *
     * @param request DTO с данными для создания
     * @return DTO созданного блюда
     * @throws ConflictException если блюдо с таким названием уже существует
     */
    @Transactional
    public DishResponse createDish(DishRequest request) {
        if (dishRepository.existsByName(request.getName())) {
            throw new ConflictException("Блюдо с названием " + request.getName() + " уже существует");
        }
        Dish dish = dishMapper.toEntity(request);
        return dishMapper.toResponse(dishRepository.save(dish));
    }

    /**
     * Получает список всех блюд.
     *
     * @return список DTO блюд
     */
    @Transactional(readOnly = true)
    public List<DishResponse> getAllDishes() {
        return dishRepository.findAll().stream()
                .map(dishMapper::toResponse)
                .collect(Collectors.toList());
    }
}