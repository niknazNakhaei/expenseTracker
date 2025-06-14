package com.sample.expense.service.impl.transactional;

import com.sample.expense.entity.MonthlyReport;
import com.sample.expense.exception.InternalExpenseException;
import com.sample.expense.exception.NotFoundMonthlyReportException;
import com.sample.expense.repository.MonthlyReportDao;
import com.sample.expense.service.SentEventService;
import com.sample.expense.service.transactional.ReadOnlyMonthlyReportService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class TransactionalMonthlyReportServiceImpl implements ReadOnlyMonthlyReportService {

    private final MonthlyReportDao monthlyReportDao;
    private final SentEventService sentEventService;

    @Override
    @Transactional(readOnly = true)
    public Optional<MonthlyReport> findMonthlyReportByCategoryId(Long categoryId, LocalDateTime fromDate, LocalDateTime toDate) {
        return monthlyReportDao.findMonthlyReportByCategory_IdAndFromDateAndToDate(categoryId, fromDate, toDate);
    }

    @Transactional
    public void saveMonthlyReport(MonthlyReport monthlyReport) throws InternalExpenseException {
        monthlyReportDao.save(monthlyReport);
        sentEventService.updateSentEvent(monthlyReport);
    }

    @Transactional
    public void updateMonthlyReport(MonthlyReport monthlyReport) throws NotFoundMonthlyReportException {
        monthlyReportDao.findById(monthlyReport.getId()).ifPresentOrElse(
                existMonthlyReport -> {
                    existMonthlyReport.setCumulativeAmount(monthlyReport.getCumulativeAmount());
                    existMonthlyReport.setAlert(monthlyReport.getAlert());
                    monthlyReportDao.save(existMonthlyReport);
                    try {
                        sentEventService.updateSentEvent(monthlyReport);
                    } catch (InternalExpenseException e) {
                        log.warn("An exception occurred while updating sent event", e);
                        throw e;
                    }
                },
                () -> {
                    throw new NotFoundMonthlyReportException("Monthly Report Not Found");
                });
    }
}
