package ru.mzuev.nutrilogapi.mapper;

import ru.mzuev.nutrilogapi.dto.UserRequest;
import ru.mzuev.nutrilogapi.dto.UserResponse;
import ru.mzuev.nutrilogapi.model.User;
import ru.mzuev.nutrilogapi.util.CalorieCalculator;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequest request) {
        User user = User.create(
                request.getName(),
                request.getEmail(),
                request.getAge(),
                request.getWeight(),
                request.getHeight(),
                request.getTargetType()
        );
        user.setDailyCalorieNorm(CalorieCalculator.calculate(user));
        return user;
    }

    public UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setAge(user.getAge());
        response.setWeight(user.getWeight());
        response.setHeight(user.getHeight());
        response.setTargetType(user.getTargetType());
        response.setDailyCalorieNorm(user.getDailyCalorieNorm());
        return response;
    }
}