package com.sample.expense.service.impl;

import com.sample.expense.dto.ExpenseDto;
import com.sample.expense.entity.Expense;
import com.sample.expense.entity.SentEvent;
import com.sample.expense.exception.InternalExpenseException;
import com.sample.expense.exception.NotFoundExpenseException;
import com.sample.expense.mapper.ExpenseMapper;
import com.sample.expense.service.ExpenseService;
import com.sample.expense.service.impl.transactional.TransactionalExpenseServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final TransactionalExpenseServiceImpl expenseService;
    private final ExpenseMapper mapper;

    @Override
    public void saveExpense(ExpenseDto dto) throws InternalExpenseException {
        try {
            log.info("Try to save expense: {} with amount:{}", dto.getCategoryName(), dto.getAmount());
            Expense expense = mapper.mapToEntity(dto);
            expense.setCreationTime(LocalDateTime.now());
            SentEvent sentEvent = expenseService.saveExpense(expense);
        } catch (Exception e) {
            throw new InternalExpenseException(e.getMessage());
        }
    }

    @Override
    public void updateExpense(ExpenseDto dto) throws InternalExpenseException, NotFoundExpenseException {
        try {
            log.info("Try to update expense: {} and amount: {}", dto.getCategoryName(), dto.getAmount());
            Expense expense = mapper.mapToEntity(dto);
            expense.setUpdatedTime(LocalDateTime.now());
            SentEvent sentEvent = expenseService.updateExpense(expense);
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
        } catch (Exception e) {
            throw new InternalExpenseException(e.getMessage(), e);
        }
    }

    public void sentToKafkaConsumer() {

    }
}
