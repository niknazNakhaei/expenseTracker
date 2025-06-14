package com.sample.expense.service;

import com.sample.expense.entity.MonthlyReport;
import com.sample.expense.exception.InternalExpenseException;
import com.sample.expense.exception.NotFoundMonthlyReportException;
import com.sample.expense.service.transactional.ReadOnlyMonthlyReportService;

public interface MonthlyReportService extends ReadOnlyMonthlyReportService {

    void saveMonthlyReport(MonthlyReport monthlyReport) throws InternalExpenseException;

    void updateMonthlyReport(MonthlyReport monthlyReport) throws InternalExpenseException, NotFoundMonthlyReportException;
}
