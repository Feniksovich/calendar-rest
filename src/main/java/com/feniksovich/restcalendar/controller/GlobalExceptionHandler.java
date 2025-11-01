package com.feniksovich.restcalendar.controller;

import com.feniksovich.restcalendar.dto.validation.ValidationErrorResponse;
import com.feniksovich.restcalendar.dto.validation.Violation;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.function.Function;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final Function<ConstraintViolation<?>, String> EXTRACT_PARAM_NAME =
            v -> v.getPropertyPath().toString().replaceAll(".*\\.", "");

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse onConstraintValidationException(ConstraintViolationException ex) {
        LOGGER.debug("Constraint validation error: {}", ex.getMessage());
        final List<Violation> violations = ex.getConstraintViolations().stream()
                .map(v -> new Violation(EXTRACT_PARAM_NAME.apply(v), v.getMessage()))
                .toList();
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse onMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        LOGGER.debug("Method argument type mismatch error: {}", ex.getMessage());
        final Violation violation = new Violation(
                ex.getParameter().getParameterName(),
                "Parameter type '%s' required".formatted(ex.getParameter().getParameterType().getSimpleName())
        );
        return new ValidationErrorResponse(violation);
    }
}
