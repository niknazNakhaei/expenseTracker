package com.sample.expense.service.impl;

import com.sample.expense.entity.MonthlyReport;
import com.sample.expense.exception.InternalExpenseException;
import com.sample.expense.exception.NotFoundMonthlyReportException;
import com.sample.expense.service.MonthlyReportService;
import com.sample.expense.service.impl.transactional.TransactionalMonthlyReportServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class MonthlyReportServiceImpl implements MonthlyReportService {

    private final TransactionalMonthlyReportServiceImpl monthlyReportService;

    @Override
    public Optional<MonthlyReport> findMonthlyReportByCategoryId(Long categoryId, LocalDateTime fromDate, LocalDateTime toDate) {
        return monthlyReportService.findMonthlyReportByCategoryId(categoryId, fromDate, toDate);
    }

    @Override
    public void saveMonthlyReport(MonthlyReport monthlyReport) throws InternalExpenseException {
        try {
            log.info("Try to Save monthly report: {}", monthlyReport);
            monthlyReportService.saveMonthlyReport(monthlyReport);
        } catch (Exception e) {
            throw new InternalExpenseException(e.getMessage(), e);
        }
    }

    @Override
    public void updateMonthlyReport(MonthlyReport monthlyReport) throws InternalExpenseException, NotFoundMonthlyReportException {
        try {
            log.info("Try to update monthly report: {}", monthlyReport);
            monthlyReportService.updateMonthlyReport(monthlyReport);
        } catch (NotFoundMonthlyReportException e) {
            log.warn("Monthly report could not be found: {}", monthlyReport);
        } catch (InternalExpenseException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalExpenseException(e.getMessage(), e);
        }
    }
}
