package com.sample.expense.mapper;

import com.sample.expense.dto.MonthlyReportDto;
import com.sample.expense.entity.Category;
import com.sample.expense.entity.MonthlyReport;
import com.sample.expense.entity.projection.MonthlyExpense;
import com.sample.expense.util.TimeUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

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

    public List<MonthlyReportDto> mapToDtoList(List<MonthlyReport> monthlyReports) {
        return monthlyReports.stream().map(monthlyReport -> {
            MonthlyReportDto monthlyReportDto = new MonthlyReportDto();
            monthlyReportDto.setCategoryName(monthlyReport.getCategory().getName());
            monthlyReportDto.setCategoryId(monthlyReport.getCategory().getId());
            monthlyReportDto.setFromDate(monthlyReport.getFromDate());
            monthlyReportDto.setToDate(monthlyReport.getToDate());
            monthlyReportDto.setCumulativeAmount(monthlyReport.getCumulativeAmount());
            monthlyReportDto.setAlert(monthlyReport.getAlert());
            monthlyReportDto.setUpdatedTime(monthlyReport.getUpdatedTime());
            monthlyReportDto.setCreationTime(monthlyReport.getCreationTime());
            return monthlyReportDto;
        }).toList();
    }
}
