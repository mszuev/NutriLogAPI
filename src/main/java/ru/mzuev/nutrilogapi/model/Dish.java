package ru.mzuev.nutrilogapi.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

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

    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
    }

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