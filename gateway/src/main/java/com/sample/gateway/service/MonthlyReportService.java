package com.sample.gateway.service;

import com.sample.gateway.dto.request.MonthlyReportSearchDto;
import com.sample.gateway.dto.response.MonthlyReportResponseSearch;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "monthlyReport", url = "localhost:6060/expense/monthly_report")
public interface MonthlyReportService {

    @PostMapping(path = "/search", produces = "application/json", consumes = "application/json")
    MonthlyReportResponseSearch searchMonthlyReport(@RequestBody MonthlyReportSearchDto searchDto);
}
