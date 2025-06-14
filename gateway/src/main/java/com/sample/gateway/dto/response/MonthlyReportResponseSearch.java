package com.sample.gateway.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class MonthlyReportResponseSearch {
    private List<MonthlyReportDto> monthlyReportDtoList;
}
