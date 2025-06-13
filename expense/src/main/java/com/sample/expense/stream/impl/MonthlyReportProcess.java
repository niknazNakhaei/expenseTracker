package com.sample.expense.stream.impl;

import com.sample.expense.entity.projection.MonthlyExpense;
import com.sample.expense.repository.MonthlyReportDao;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.streams.kstream.ForeachAction;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MonthlyReportProcess implements ForeachAction<String, MonthlyExpense> {

    private final MonthlyReportDao monthlyReportDao;

    @Override
    public void apply(String s, MonthlyExpense monthlyExpense) {

    }
}
