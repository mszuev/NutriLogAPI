package ru.mzuev.nutrilogapi.repository;

import ru.mzuev.nutrilogapi.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

/**
 * Репозиторий для работы с приемами пищи
 */
public interface MealRepository extends JpaRepository<Meal, Long> {

    /**
     * Находит приемы пищи по пользователю и дате
     * @param userId ID пользователя
     * @param date дата приема пищи
     * @return список приемов пищи
     */
    List<Meal> findByUserIdAndDate(Long userId, LocalDate date);
}