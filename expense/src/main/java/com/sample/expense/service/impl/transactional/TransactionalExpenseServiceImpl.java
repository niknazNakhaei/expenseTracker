package com.sample.expense.service.impl.transactional;

import com.sample.expense.entity.Expense;
import com.sample.expense.entity.SentEvent;
import com.sample.expense.exception.NotFoundExpenseException;
import com.sample.expense.mapper.SentEventMapper;
import com.sample.expense.repository.ExpenseDao;
import com.sample.expense.repository.SentEventDao;
import com.sample.expense.service.transactional.ReadOnlyExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionalExpenseServiceImpl implements ReadOnlyExpenseService {

    private final ExpenseDao expenseDao;
    private final SentEventDao sentEventDao;
    private final SentEventMapper sentEventMapper;

    @Transactional
    public SentEvent saveExpense(Expense expense) {
        expenseDao.save(expense);
        return sentEventDao.save(sentEventMapper.mapToEntity(expense.getCategory()));
    }

    @Transactional
    public SentEvent updateExpense(Expense expense) {
        return expenseDao.findById(expense.getId()).map(existExpense -> {
            existExpense.setAmount(expense.getAmount());
            existExpense.setDescription(expense.getDescription());
            existExpense.setExpenseTime(expense.getExpenseTime());
            expenseDao.save(existExpense);
            return sentEventDao.save(sentEventMapper.mapToEntity(expense.getCategory()));
        }).orElseThrow(() -> new NotFoundExpenseException("Expense not found"));
    }

    @Transactional
    public SentEvent deleteExpense(Long expenseId) {
        return expenseDao.findById(expenseId).map(existExpense -> {
            expenseDao.delete(existExpense);
            return sentEventDao.save(sentEventMapper.mapToEntity(existExpense.getCategory()));
        }).orElseThrow(() -> new NotFoundExpenseException("Expense not found"));
    }
}
