package com.sample.gateway.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class MonthlyReportDto {

    private String categoryName;
    private Long categoryId;
    private BigDecimal cumulativeAmount;
    private String alert;
    private LocalDate fromDate;
    private LocalDate toDate;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
