package ru.mzuev.nutrilogapi.repository;

import ru.mzuev.nutrilogapi.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {
    boolean existsByName(String name);
}