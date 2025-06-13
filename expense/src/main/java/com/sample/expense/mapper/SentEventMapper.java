package com.sample.expense.mapper;

import com.sample.expense.entity.Category;
import com.sample.expense.entity.SentEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SentEventMapper {

    public SentEvent mapToEntity(Category category, LocalDateTime expenseTime) {
        SentEvent sentEvent = new SentEvent();
        sentEvent.setCategory(category);
        sentEvent.setExpenseTime(expenseTime);
        sentEvent.setProcessed(Boolean.FALSE);
        sentEvent.setCreationTime(LocalDateTime.now());
        return sentEvent;
    }
}
