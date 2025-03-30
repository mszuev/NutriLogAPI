package ru.mzuev.nutrilogapi.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "meals")
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDate date;

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MealDish> dishes = new ArrayList<>();

    @Column(name = "total_calories")
    private Integer totalCalories;

    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
        calculateTotalCalories();
    }

    public void calculateTotalCalories() {
        this.totalCalories = dishes.stream()
                .mapToInt(dish -> dish.getPortions() * dish.getDish().getCaloriesPerServing())
                .sum();
    }

    public static Meal create(User user, LocalDate date) {
        Meal meal = new Meal();
        meal.setUser(user);
        meal.setDate(date);
        return meal;
    }
}