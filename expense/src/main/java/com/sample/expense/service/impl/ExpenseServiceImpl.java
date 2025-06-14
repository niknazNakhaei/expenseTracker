package com.sample.expense.service.impl;

import com.sample.expense.dto.ExpenseDto;
import com.sample.expense.dto.ExpenseResponseSearch;
import com.sample.expense.dto.ExpenseSearchDto;
import com.sample.expense.dto.ExpenseUpdateDto;
import com.sample.expense.entity.Expense;
import com.sample.expense.entity.SentEvent;
import com.sample.expense.exception.InternalExpenseException;
import com.sample.expense.exception.NotFoundExpenseException;
import com.sample.expense.mapper.ExpenseMapper;
import com.sample.expense.service.ExpenseService;
import com.sample.expense.service.impl.transactional.TransactionalExpenseServiceImpl;
import com.sample.expense.util.Topic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseMapper mapper;
    private final StreamBridge streamBridge;
    private final TransactionalExpenseServiceImpl expenseService;

    @Override
    public void saveExpense(ExpenseDto dto) throws InternalExpenseException {
        try {
            log.info("Try to save expense: {} with amount:{}", dto.getCategoryName(), dto.getAmount());
            Expense expense = mapper.mapToEntity(dto);
            expense.setCreatedTime(LocalDateTime.now());
            SentEvent sentEvent = expenseService.saveExpense(expense);
            sentToKafkaConsumer(sentEvent);
        } catch (Exception e) {
            throw new InternalExpenseException(e.getMessage());
        }
    }

    @Override
    public void updateExpense(ExpenseUpdateDto dto) throws InternalExpenseException, NotFoundExpenseException {
        try {
            log.info("Try to update expense: {} and amount: {}", dto.getCategoryName(), dto.getAmount());
            Expense expense = mapper.mapToEntity(dto);
            expense.setUpdatedTime(LocalDateTime.now());
            SentEvent sentEvent = expenseService.updateExpense(expense);
            sentToKafkaConsumer(sentEvent);
        } catch (NotFoundExpenseException e) {
            log.warn("expense : {} not found", dto.getCategoryName());
        } catch (Exception e) {
            throw new InternalExpenseException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteExpense(Long expenseId) throws InternalExpenseException {
        try {
            log.info("Try to delete expense: {}", expenseId);
            SentEvent sentEvent = expenseService.deleteExpense(expenseId);
            sentToKafkaConsumer(sentEvent);
        } catch (Exception e) {
            throw new InternalExpenseException(e.getMessage(), e);
        }
    }

    public void sentToKafkaConsumer(SentEvent sentEvent) {
        log.info("Sent to Kafka consumer: {}", sentEvent);
        streamBridge.send(Topic.EXPENSE.getTopicName(),
                MessageBuilder.withPayload(sentEvent)
                        .setHeader("KEY", sentEvent.getCategory().getName()).build());
    }

    @Override
    public ExpenseResponseSearch searchExpense(ExpenseSearchDto searchDto) {
        return expenseService.searchExpense(searchDto);
    }
}
