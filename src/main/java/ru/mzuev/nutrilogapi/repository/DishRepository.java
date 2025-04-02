package ru.mzuev.nutrilogapi.repository;

import ru.mzuev.nutrilogapi.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с блюдами
 */
public interface DishRepository extends JpaRepository<Dish, Long> {

    /**
     * Проверяет существование блюда по названию
     * @param name название блюда
     * @return true если блюдо существует
     */
    boolean existsByName(String name);
}