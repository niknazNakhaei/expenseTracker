package com.sample.expense.stream.impl;

import com.sample.expense.entity.SentEvent;
import com.sample.expense.entity.projection.MonthlyExpense;
import com.sample.expense.repository.ExpenseDao;
import com.sample.expense.util.TimeUtil;
import lombok.AllArgsConstructor;
import org.apache.kafka.streams.kstream.ValueMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class MonthlyExpenseValueMapper implements ValueMapper<SentEvent, Iterable<MonthlyExpense>> {

    private final ExpenseDao expenseDao;

    @Override
    @Transactional(readOnly = true)
    public Iterable<MonthlyExpense> apply(SentEvent sentEvent) {
        LocalDateTime fromDate = TimeUtil.generateFirstDayOfMonth(sentEvent.getExpenseTime());
        LocalDateTime toDate = TimeUtil.generateLastDayOfMonth(sentEvent.getExpenseTime());
        return List.of(expenseDao.findMonthlyExpenseByCategoryIdAndUserId(sentEvent.getCategory().getId(), sentEvent.getCategory().getUserId(), fromDate, toDate));
    }
}
