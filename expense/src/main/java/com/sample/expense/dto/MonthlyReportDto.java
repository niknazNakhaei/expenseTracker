package com.sample.expense.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MonthlyReportDto {

    private String categoryName;
    private Long categoryId;
    private BigDecimal cumulativeAmount;
    private String alert;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private LocalDateTime creationTime;
    private LocalDateTime updatedTime;
}
