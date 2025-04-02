package ru.mzuev.nutrilogapi.exception;

/**
 * Исключение, возникающее при конфликте данных
 * (например, попытка создать дубликат сущности)
 */
public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}