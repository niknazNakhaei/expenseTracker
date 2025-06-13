package com.sample.expense.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotFoundCategoryException extends RuntimeException{

    public NotFoundCategoryException(String message) {
        super(message);
    }
    public NotFoundCategoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
