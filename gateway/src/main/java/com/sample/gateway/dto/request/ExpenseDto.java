package com.sample.gateway.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ExpenseDto {
    private String categoryName;
    @NotNull
    private Long categoryId;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private LocalDateTime expenseTime;
    private String description;
}
