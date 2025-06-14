package com.sample.expense.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ExpenseSearchDto {
    private String categoryName;
    private Long categoryId;
    private BigDecimal amount;
    private LocalDateTime expenseFromDate;
    private LocalDateTime expenseToDate;
}
