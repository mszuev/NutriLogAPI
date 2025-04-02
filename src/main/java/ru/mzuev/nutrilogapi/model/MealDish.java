package ru.mzuev.nutrilogapi.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Связующая сущность между Meal и Dish.
 * * Хранит информацию о количестве порций конкретного блюда в приеме пищи.
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "meal_dishes")
public class MealDish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_id", nullable = false)
    private Meal meal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id", nullable = false)
    private Dish dish;

    @Column(nullable = false)
    private Integer portions;

    /**
     * Создает связь между приемом пищи и блюдом с указанным количеством порций.
     *
     * @param meal прием пищи
     * @param dish блюдо
     * @param portions количество порций
     * @return новый объект MealDish
     */
    public static MealDish create(Meal meal, Dish dish, int portions) {
        MealDish mealDish = new MealDish();
        mealDish.setMeal(meal);
        mealDish.setDish(dish);
        mealDish.setPortions(portions);
        return mealDish;
    }
}