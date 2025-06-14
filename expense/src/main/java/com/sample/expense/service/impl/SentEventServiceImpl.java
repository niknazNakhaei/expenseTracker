package com.sample.expense.service.impl;

import com.sample.expense.entity.Category;
import com.sample.expense.entity.MonthlyReport;
import com.sample.expense.entity.SentEvent;
import com.sample.expense.exception.InternalExpenseException;
import com.sample.expense.mapper.SentEventMapper;
import com.sample.expense.service.SentEventService;
import com.sample.expense.service.impl.transactional.TransactionalSentEventServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class SentEventServiceImpl implements SentEventService {

    private final TransactionalSentEventServiceImpl sentEventService;
    private final SentEventMapper sentEventMapper;

    @Override
    public SentEvent saveSentEvent(Category category, LocalDateTime expenseTime) throws InternalExpenseException {
        try {
            log.info("Try to save sent event by category: {}", category.getName());
            SentEvent sentEvent = sentEventMapper.mapToEntity(category, expenseTime);
            return sentEventService.saveSentEvent(sentEvent);
        } catch (Exception e) {
            throw new InternalExpenseException(e.getMessage(), e);
        }
    }

    @Override
    public void updateSentEvent(MonthlyReport monthlyReport) throws InternalExpenseException {
        try {
            log.info("Try to update sent event by category: {}", monthlyReport.getCategory());
            sentEventService.updateSentEvent(monthlyReport);
        } catch (Exception e) {
            throw new InternalExpenseException(e.getMessage(), e);
        }
    }
}
