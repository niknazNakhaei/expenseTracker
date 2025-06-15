package com.sample.expense.stream.impl;

import com.sample.expense.entity.MonthlyReport;
import com.sample.expense.entity.projection.MonthlyExpense;
import com.sample.expense.mapper.MonthlyReportMapper;
import com.sample.expense.service.MonthlyReportService;
import com.sample.expense.util.TimeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.ForeachAction;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class MonthlyReportProcess implements ForeachAction<String, MonthlyExpense> {

    private final MonthlyReportService monthlyReportService;
    private final MonthlyReportMapper monthlyReportMapper;

    @Override
    public void apply(String key, MonthlyExpense monthlyExpense) {
        try {
            Optional<MonthlyReport> lastMonthReport = findMonthReport(monthlyExpense, Boolean.TRUE);
            if (lastMonthReport.isPresent()) {
                log.info("last Month report has found : {} ", lastMonthReport.get());
                String alert = generateAlert(monthlyExpense, lastMonthReport.get());
                addOrUpdateMonthlyReport(monthlyExpense, alert);
            } else {
                addOrUpdateMonthlyReport(monthlyExpense, null);
            }
        } catch (Exception e) {
            log.warn("An exception occurred during the category: {} processing", monthlyExpense.getCategoryId());
        }
    }

    private Optional<MonthlyReport> findMonthReport(MonthlyExpense monthlyExpense, Boolean lastMonthReport) {
        LocalDateTime fromDate = TimeUtil.generateFirstDayOfMonth(monthlyExpense.getExpenseTime());
        LocalDateTime toDate = TimeUtil.generateLastDayOfMonth(monthlyExpense.getExpenseTime());
        if (lastMonthReport) {
            fromDate = fromDate.minusMonths(1L);
            toDate = toDate.minusMonths(1L);
        }
        return monthlyReportService.findMonthlyReportByCategoryId
                (monthlyExpense.getCategoryId(), fromDate, toDate);
    }

    private String generateAlert(MonthlyExpense monthlyExpense, MonthlyReport lastReport) {
        String description = "";
        if (monthlyExpense.getMonthlyExpense().compareTo(lastReport.getCumulativeAmount()) > 0) {
            description = String.format("spending too much money on %s as always", lastReport.getCategory().getName());
        }
        return description;
    }

    private void addOrUpdateMonthlyReport(MonthlyExpense monthlyExpense, String alert) {
        findMonthReport(monthlyExpense, false).ifPresentOrElse(
                monthlyReport -> {
                    monthlyReport.setCumulativeAmount(monthlyExpense.getMonthlyExpense());
                    if (StringUtils.hasLength(alert)) {
                        monthlyReport.setAlert(alert);
                    }
                    monthlyReportService.updateMonthlyReport(monthlyReport);
                },
                () -> {
                    MonthlyReport monthlyReport = monthlyReportMapper.mapToEntity(monthlyExpense, alert);
                    monthlyReportService.saveMonthlyReport(monthlyReport);
                }
        );
    }
}

