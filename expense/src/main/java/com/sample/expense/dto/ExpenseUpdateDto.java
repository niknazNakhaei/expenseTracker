package com.sample.expense.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ExpenseUpdateDto {
    private Long id;
    private String categoryName;
    private Long categoryId;
    private BigDecimal amount;
    private LocalDateTime expenseTime;
    private String description;
}
