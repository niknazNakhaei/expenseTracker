package com.sample.expense.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Topic {

    EXPENSE("expense-processor","processExpense-in-0");

    private final String topicName;
    private final String processorName;
}
