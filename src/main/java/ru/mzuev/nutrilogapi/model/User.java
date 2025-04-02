package ru.mzuev.nutrilogapi.model;

import jakarta.persistence.*;
import lombok.*;
import ru.mzuev.nutrilogapi.model.enums.TargetType;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private Integer age;

    @Column(name = "weight_kg", nullable = false)
    private Double weight;

    @Column(name = "height_cm", nullable = false)
    private Double height;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TargetType targetType;

    @Column(name = "daily_calorie_norm", nullable = false)
    private Double dailyCalorieNorm;

    public static User create(String name, String email, int age, double weight,
                              double height, TargetType targetType) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setAge(age);
        user.setWeight(weight);
        user.setHeight(height);
        user.setTargetType(targetType);
        return user;
    }
}