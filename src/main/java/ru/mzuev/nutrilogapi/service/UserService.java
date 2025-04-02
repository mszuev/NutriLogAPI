package ru.mzuev.nutrilogapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mzuev.nutrilogapi.dto.UserRequest;
import ru.mzuev.nutrilogapi.dto.UserResponse;
import ru.mzuev.nutrilogapi.exception.ConflictException;
import ru.mzuev.nutrilogapi.exception.ResourceNotFoundException;
import ru.mzuev.nutrilogapi.mapper.UserMapper;
import ru.mzuev.nutrilogapi.model.User;
import ru.mzuev.nutrilogapi.repository.UserRepository;

/**
 * Сервис для операций с пользователями.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * Создает нового пользователя.
     *
     * @param request DTO с данными пользователя
     * @return DTO созданного пользователя
     * @throws ConflictException если пользователь с таким email уже существует
     */
    @Transactional
    public UserResponse createUser(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ConflictException("Email " + request.getEmail() + " уже существует");
        }
        User user = userMapper.toEntity(request);
        return userMapper.toResponse(userRepository.save(user));
    }

    /**
     * Получает пользователя по ID.
     *
     * @param id ID пользователя
     * @return DTO с данными пользователя
     * @throws ResourceNotFoundException если пользователь не найден
     */
    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден с id: " + id));
        return userMapper.toResponse(user);
    }
}