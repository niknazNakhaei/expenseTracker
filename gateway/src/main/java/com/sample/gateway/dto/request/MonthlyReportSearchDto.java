package com.sample.gateway.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MonthlyReportSearchDto {
    private String categoryName;
    private Long categoryId;
    private BigDecimal cumulativeAmount;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
}
