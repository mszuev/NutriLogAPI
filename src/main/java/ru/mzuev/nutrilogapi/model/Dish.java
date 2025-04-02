package ru.mzuev.nutrilogapi.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Сущность блюда
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "dishes")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "calories_per_serving", nullable = false)
    private Integer caloriesPerServing;

    @Column(nullable = false)
    private Double proteins;

    @Column(nullable = false)
    private Double fats;

    @Column(nullable = false)
    private Double carbohydrates;

    /**
     * Создает новое блюдо с указанными параметрами.
     *
     * @param name название блюда
     * @param calories калории на порцию
     * @param proteins количество белков (в граммах)
     * @param fats количество жиров (в граммах)
     * @param carbs количество углеводов (в граммах)
     * @return новый объект Dish
     */
    public static Dish create(String name, int calories, double proteins, double fats, double carbs) {
        Dish dish = new Dish();
        dish.setName(name);
        dish.setCaloriesPerServing(calories);
        dish.setProteins(proteins);
        dish.setFats(fats);
        dish.setCarbohydrates(carbs);
        return dish;
    }
}