package com.sample.expense.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InternalExpenseException extends RuntimeException {

    public InternalExpenseException(String message) {
        super(message);
    }

    public InternalExpenseException(String message, Throwable cause) {
        super(message, cause);
    }
}
