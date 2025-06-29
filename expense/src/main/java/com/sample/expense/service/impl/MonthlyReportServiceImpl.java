package com.sample.expense.service.impl;

import com.sample.expense.dto.MonthlyReportResponseSearch;
import com.sample.expense.dto.MonthlyReportSearchDto;
import com.sample.expense.entity.MonthlyReport;
import com.sample.expense.exception.InternalExpenseException;
import com.sample.expense.exception.NotFoundMonthlyReportException;
import com.sample.expense.service.MonthlyReportService;
import com.sample.expense.service.impl.transactional.TransactionalMonthlyReportServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class MonthlyReportServiceImpl implements MonthlyReportService {

    private final TransactionalMonthlyReportServiceImpl monthlyReportService;

    @Override
    public Optional<MonthlyReport> findMonthlyReportByCategoryId(Long categoryId, LocalDate fromDate, LocalDate toDate) {
        return monthlyReportService.findMonthlyReportByCategoryId(categoryId, fromDate, toDate);
    }

    @Transactional
    @Override
    public void saveMonthlyReport(MonthlyReport monthlyReport) throws InternalExpenseException {
        try {
            log.info("Try to Save monthly report: {}", monthlyReport);
            monthlyReportService.saveMonthlyReport(monthlyReport);
        } catch (Exception e) {
            throw new InternalExpenseException(e.getMessage(), e);
        }

    }

    @Transactional
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

    @Transactional(readOnly = true)
    @Override
    public MonthlyReportResponseSearch searchMonthlyReport(MonthlyReportSearchDto searchDto) {
        return monthlyReportService.searchMonthlyReport(searchDto);
    }
}
