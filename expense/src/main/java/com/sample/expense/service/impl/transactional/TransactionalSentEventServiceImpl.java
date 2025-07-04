package com.sample.expense.service.impl.transactional;

import com.sample.expense.entity.MonthlyReport;
import com.sample.expense.entity.SentEvent;
import com.sample.expense.exception.NotFoundSentEventException;
import com.sample.expense.repository.SentEventDao;
import com.sample.expense.service.transactional.ReadOnlySentEventService;
import com.sample.expense.util.TimeUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@AllArgsConstructor
public class TransactionalSentEventServiceImpl implements ReadOnlySentEventService {

    private final SentEventDao sentEventDao;

    @Transactional
    public SentEvent saveSentEvent(SentEvent sentEvent) {
        return sentEventDao.findByCategory_idAndExpenseTimeBetween(sentEvent.getCategory().getId(),
                        TimeUtil.generateFirstDayOfMonth(sentEvent.getExpenseTime()),
                        TimeUtil.generateLastDayOfMonth(sentEvent.getExpenseTime()))
                .map(existSentEvent -> {
                    existSentEvent.setProcessed(Boolean.FALSE);
                    existSentEvent.setExpenseTime(sentEvent.getExpenseTime());
                    existSentEvent.setUpdatedTime(LocalDateTime.now());
                    return existSentEvent;
                }).orElseGet(() -> sentEventDao.save(sentEvent));
    }

    @Transactional
    public void updateSentEvent(MonthlyReport monthlyReport) {
        sentEventDao.findByCategory_idAndExpenseTimeBetween(monthlyReport.getCategory().getId(), monthlyReport.getFromDate().atStartOfDay(), monthlyReport.getToDate().atTime(LocalTime.MAX)).ifPresentOrElse(
                sentEvent -> sentEvent.setProcessed(Boolean.TRUE),
                () -> {
                    throw new NotFoundSentEventException("Sent event not found");
                }
        );
    }
}
