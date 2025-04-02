package ru.mzuev.nutrilogapi.exception;

/**
 * Исключение, возникающее при отсутствии запрашиваемого ресурса
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}