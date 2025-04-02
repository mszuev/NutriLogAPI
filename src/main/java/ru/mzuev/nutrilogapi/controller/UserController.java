package ru.mzuev.nutrilogapi.controller;

import ru.mzuev.nutrilogapi.dto.UserRequest;
import ru.mzuev.nutrilogapi.dto.UserResponse;
import ru.mzuev.nutrilogapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для управления пользователями.
 */
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Создает нового пользователя.
     *
     * @param request DTO с данными пользователя
     * @return созданный пользователь
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody UserRequest request) {
        return userService.createUser(request);
    }

    /**
     * Получает пользователя по ID.
     *
     * @param id ID пользователя
     * @return DTO с данными пользователя
     */
    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}