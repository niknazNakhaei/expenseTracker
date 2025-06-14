package com.sample.expense.mapper;

import com.sample.expense.entity.Category;
import com.sample.expense.entity.MonthlyReport;
import com.sample.expense.entity.projection.MonthlyExpense;
import com.sample.expense.util.TimeUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Component
public class MonthlyReportMapper {

    public MonthlyReport mapToEntity(MonthlyExpense monthlyExpense, String alert) {
        MonthlyReport monthlyReport = new MonthlyReport();
        monthlyReport.setCumulativeAmount(monthlyExpense.getMonthlyExpense());
        Category category = new Category();
        category.setId(monthlyExpense.getCategoryId());
        monthlyReport.setCategory(category);
        monthlyReport.setFromDate(TimeUtil.generateFirstDayOfMonth(monthlyExpense.getExpenseTime()));
        monthlyReport.setToDate(TimeUtil.generateLastDayOfMonth(monthlyExpense.getExpenseTime()));
        if(StringUtils.hasLength(alert)) {
            monthlyReport.setAlert(alert);
        }
        monthlyReport.setCreationTime(LocalDateTime.now());
        return monthlyReport;
    }
}
