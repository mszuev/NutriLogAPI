package ru.mzuev.nutrilogapi.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.FieldError;
import java.net.URI;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Глобальный обработчик исключений.
 * Преобразует исключения в стандартизированные ответы ProblemDetail.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler{

    /**
     * Обрабатывает исключение {@link ResourceNotFoundException}.
     * Возвращает ответ с HTTP-статусом 404 (Not Found) и деталями ошибки.
     *
     * @param ex перехваченное исключение
     * @return объект {@link ProblemDetail} с кодом 404, типом ошибки и сообщением
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFound(ResourceNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problem.setType(URI.create("/errors/not-found"));
        problem.setTitle("Ресурс не найден");
        return problem;
    }

    /**
     * Обрабатывает исключение {@link ConflictException}.
     * Возвращает ответ с HTTP-статусом 409 (Conflict) и деталями конфликта.
     *
     * @param ex перехваченное исключение
     * @return объект {@link ProblemDetail} с кодом 409, типом ошибки и сообщением
     */
    @ExceptionHandler(ConflictException.class)
    public ProblemDetail handleConflict(ConflictException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        problem.setType(URI.create("/errors/conflict"));
        problem.setTitle("Конфликт данных");
        return problem;
    }

    /**
     * Обрабатывает ошибки валидации {@link MethodArgumentNotValidException}.
     * Возвращает ответ с HTTP-статусом 400 (Bad Request), списком ошибок и их описанием.
     *
     * @param ex перехваченное исключение
     * @return объект {@link ProblemDetail} с кодом 400, типом ошибки и списком полей с ошибками
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationExceptions(MethodArgumentNotValidException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "Проверьте корректность введенных данных"
        );
        problemDetail.setTitle("Ошибка валидации");
        problemDetail.setType(URI.create("/errors/validation"));

        // Собираем ошибки в формате { "field": "message" }
        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        DefaultMessageSourceResolvable::getDefaultMessage // Берём сообщение из messages.properties
                ));

        problemDetail.setProperty("errors", errors);
        return problemDetail;
    }

    /**
     * Обрабатывает все непредвиденные исключения.
     * Возвращает ответ с HTTP-статусом 500 (Internal Server Error) и общим сообщением об ошибке.
     * Детали ошибки логируются в консоль.
     *
     * @param ex перехваченное исключение
     * @return объект {@link ProblemDetail} с кодом 500 и общим описанием ошибки
     */
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGeneralException(Exception ex) {
        log.error("Непредвиденная ошибка: ", ex);

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Произошла внутренняя ошибка сервера"
        );
        problem.setType(URI.create("/errors/internal-error"));
        problem.setTitle("Внутренняя ошибка");
        return problem;
    }
}