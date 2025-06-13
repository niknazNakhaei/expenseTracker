package com.sample.expense.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotFoundExpenseException extends RuntimeException{

    public NotFoundExpenseException(String message) {
        super(message);
    }
    public NotFoundExpenseException(String message, Throwable cause) {
        super(message, cause);
    }
}
