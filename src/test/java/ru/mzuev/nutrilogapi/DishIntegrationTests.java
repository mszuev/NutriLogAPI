package ru.mzuev.nutrilogapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import ru.mzuev.nutrilogapi.dto.DishRequest;
import ru.mzuev.nutrilogapi.dto.DishResponse;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DishIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void createDishTest() {
        DishRequest dishRequest = new DishRequest();
        dishRequest.setName("Test Dish");
        dishRequest.setCaloriesPerServing(500);
        dishRequest.setProteins(20.0);
        dishRequest.setFats(10.0);
        dishRequest.setCarbohydrates(50.0);

        ResponseEntity<DishResponse> response = restTemplate.postForEntity(
                "/api/v1/dishes", dishRequest, DishResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        DishResponse dishResponse = response.getBody();
        assertThat(dishResponse).isNotNull();
        assertThat(dishResponse.getId()).isNotNull();
    }
}