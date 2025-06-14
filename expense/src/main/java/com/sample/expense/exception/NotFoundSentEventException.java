package com.sample.expense.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotFoundSentEventException extends RuntimeException {

    public NotFoundSentEventException(String message) {
        super(message);
    }
    public NotFoundSentEventException(String message, Throwable cause) {
        super(message, cause);
    }
}
