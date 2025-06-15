package com.sample.expense.stream.impl;

import com.sample.expense.entity.SentEvent;
import com.sample.expense.entity.projection.MonthlyExpense;
import com.sample.expense.repository.ExpenseDao;
import com.sample.expense.util.TimeUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.ValueMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class MonthlyExpenseValueMapper implements ValueMapper<SentEvent, Iterable<MonthlyExpense>> {

    private final ExpenseDao expenseDao;

    @Override
    @Transactional(readOnly = true)
    public Iterable<MonthlyExpense> apply(SentEvent sentEvent) {
        try {
            LocalDateTime fromDate = TimeUtil.generateFirstDayOfMonth(sentEvent.getExpenseTime());
            LocalDateTime toDate = TimeUtil.generateLastDayOfMonth(sentEvent.getExpenseTime());
            return List.of(expenseDao.findMonthlyExpenseByCategoryId(sentEvent.getCategory().getId(), fromDate, toDate));
        } catch (Exception e) {
            log.warn("An exception occurred while finding the monthly amount by categoryId : {}", sentEvent.getCategory().getId(), e);
            return Collections.emptyList();
        }
    }
}
