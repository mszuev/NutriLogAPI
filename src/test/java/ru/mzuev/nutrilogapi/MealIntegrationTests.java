package ru.mzuev.nutrilogapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import ru.mzuev.nutrilogapi.dto.*;
import ru.mzuev.nutrilogapi.model.enums.TargetType;
import java.time.LocalDate;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MealIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    private Long userId;
    private Long dishId;

    @BeforeEach
    public void setUp() {
        // Создаём пользователя
        UserRequest userRequest = new UserRequest();
        userRequest.setName("Meal Test User");
        userRequest.setEmail("mealtestuser@example.com");
        userRequest.setAge(30);
        userRequest.setWeight(70.0);
        userRequest.setHeight(175.0);
        userRequest.setTargetType(TargetType.MAINTENANCE);

        ResponseEntity<UserResponse> userResponseEntity = restTemplate.postForEntity(
                "/api/v1/users", userRequest, UserResponse.class);
        assertThat(userResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        userId = userResponseEntity.getBody().getId();

        // Создаём блюдо
        DishRequest dishRequest = new DishRequest();
        dishRequest.setName("Meal Test Dish");
        dishRequest.setCaloriesPerServing(400);
        dishRequest.setProteins(15.0);
        dishRequest.setFats(8.0);
        dishRequest.setCarbohydrates(40.0);

        ResponseEntity<DishResponse> dishResponseEntity = restTemplate.postForEntity(
                "/api/v1/dishes", dishRequest, DishResponse.class);
        assertThat(dishResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        dishId = dishResponseEntity.getBody().getId();
    }

    @Test
    public void createMealTest() {
        MealRequest mealRequest = new MealRequest();
        mealRequest.setUserId(userId);
        mealRequest.setDate(LocalDate.now());

        MealRequest.MealDishRequest mealDishRequest = new MealRequest.MealDishRequest();
        mealDishRequest.setDishId(dishId);
        mealDishRequest.setPortions(2);
        mealRequest.setDishes(Collections.singletonList(mealDishRequest));

        ResponseEntity<MealResponse> response = restTemplate.postForEntity(
                "/api/v1/meals", mealRequest, MealResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        MealResponse mealResponse = response.getBody();
        assertThat(mealResponse).isNotNull();
        // Проверяем, что общее количество калорий (400 * 2 = 800) вычисляется корректно
        assertThat(mealResponse.getTotalCalories()).isEqualTo(800);
    }

    @Test
    public void getMealsByUserAndDateTest() {
        // Создаем приём пищи
        MealRequest mealRequest = new MealRequest();
        mealRequest.setUserId(userId);
        LocalDate today = LocalDate.now();
        mealRequest.setDate(today);

        MealRequest.MealDishRequest mealDishRequest = new MealRequest.MealDishRequest();
        mealDishRequest.setDishId(dishId);
        mealDishRequest.setPortions(1);
        mealRequest.setDishes(Collections.singletonList(mealDishRequest));

        restTemplate.postForEntity("/api/v1/meals", mealRequest, MealResponse.class);

        // Получаем приёмы пищи по пользователю и дате
        String url = "/api/v1/meals/user/" + userId + "?date=" + today.toString();
        ResponseEntity<MealResponse[]> response = restTemplate.getForEntity(url, MealResponse[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        MealResponse[] meals = response.getBody();
        assertThat(meals).isNotNull();
        assertThat(meals.length).isGreaterThan(0);
    }
}
