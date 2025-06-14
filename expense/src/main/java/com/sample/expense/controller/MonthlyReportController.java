package com.sample.expense.controller;

import com.sample.expense.dto.MonthlyReportResponseSearch;
import com.sample.expense.dto.MonthlyReportSearchDto;
import com.sample.expense.service.MonthlyReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/monthly_report")
@RequiredArgsConstructor
public class MonthlyReportController {

    private final MonthlyReportService monthlyReportService;


    @PostMapping(path = "/search", consumes = "application/json", produces = "application/json")
    ResponseEntity<MonthlyReportResponseSearch> search(@RequestBody MonthlyReportSearchDto searchDto) {
        return ResponseEntity.ok(monthlyReportService.searchMonthlyReport(searchDto));
    }
}
