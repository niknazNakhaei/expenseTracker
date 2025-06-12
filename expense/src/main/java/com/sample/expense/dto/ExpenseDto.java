package com.sample.expense.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ExpenseDto {
    private Long userId;
    private Long categoryId;
    private BigDecimal amount;
    private LocalDateTime expenseDate;
    private String description;
}
