package com.sample.expense.service;

import com.sample.expense.entity.Category;
import com.sample.expense.entity.MonthlyReport;
import com.sample.expense.entity.SentEvent;
import com.sample.expense.exception.InternalExpenseException;

import java.time.LocalDateTime;

public interface SentEventService {

    SentEvent saveSentEvent(Category category, LocalDateTime expenseTime) throws InternalExpenseException;

    void updateSentEvent(MonthlyReport monthlyReport) throws InternalExpenseException;
}
