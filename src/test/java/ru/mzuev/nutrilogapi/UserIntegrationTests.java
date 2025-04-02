package ru.mzuev.nutrilogapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import ru.mzuev.nutrilogapi.dto.UserRequest;
import ru.mzuev.nutrilogapi.dto.UserResponse;
import ru.mzuev.nutrilogapi.model.enums.TargetType;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void createUserTest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("Test User");
        userRequest.setEmail("testuser@example.com");
        userRequest.setAge(30);
        userRequest.setWeight(70.0);
        userRequest.setHeight(175.0);
        userRequest.setTargetType(TargetType.MAINTENANCE);

        ResponseEntity<UserResponse> response = restTemplate.postForEntity(
                "/api/v1/users", userRequest, UserResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        UserResponse userResponse = response.getBody();
        assertThat(userResponse).isNotNull();
        assertThat(userResponse.getId()).isNotNull();
    }
}