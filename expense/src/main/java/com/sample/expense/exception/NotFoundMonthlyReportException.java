package com.sample.expense.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotFoundMonthlyReportException extends RuntimeException {

    public NotFoundMonthlyReportException(String message) {
        super(message);
    }
    public NotFoundMonthlyReportException(String message, Throwable cause) {
        super(message, cause);
    }
}
