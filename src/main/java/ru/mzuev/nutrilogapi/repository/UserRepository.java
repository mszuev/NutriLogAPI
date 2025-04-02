package ru.mzuev.nutrilogapi.repository;

import ru.mzuev.nutrilogapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с пользователями
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Проверяет существование пользователя по email
     * @param email адрес электронной почты
     * @return true если пользователь существует
     */
    boolean existsByEmail(String email);
}