package com.feniksovich.restcalendar.dto.validation;

import java.util.List;

public record ValidationErrorResponse(List<Violation> violations) {
    public ValidationErrorResponse(Violation violation) {
        this(List.of(violation));
    }
}
