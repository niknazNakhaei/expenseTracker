package com.sample.expense.service.impl.transactional;

import com.sample.expense.entity.Expense;
import com.sample.expense.entity.SentEvent;
import com.sample.expense.exception.NotFoundExpenseException;
import com.sample.expense.repository.ExpenseDao;
import com.sample.expense.service.SentEventService;
import com.sample.expense.service.transactional.ReadOnlyExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionalExpenseServiceImpl implements ReadOnlyExpenseService {

    private final ExpenseDao expenseDao;
    private final SentEventService sentEventService;

    @Transactional
    public SentEvent saveExpense(Expense expense) {
        expenseDao.save(expense);
        return sentEventService.saveSentEvent(expense.getCategory(), expense.getExpenseTime());
    }

    @Transactional
    public SentEvent updateExpense(Expense expense) {
        return expenseDao.findById(expense.getId()).map(existExpense -> {
            existExpense.setAmount(expense.getAmount());
            existExpense.setDescription(expense.getDescription());
            existExpense.setExpenseTime(expense.getExpenseTime());
            expenseDao.save(existExpense);
            return sentEventService.saveSentEvent(expense.getCategory(), expense.getExpenseTime());
        }).orElseThrow(() -> new NotFoundExpenseException("Expense not found"));
    }

    @Transactional
    public SentEvent deleteExpense(Long expenseId) {
        return expenseDao.findById(expenseId).map(existExpense -> {
            SentEvent sentEvent = sentEventService.saveSentEvent(existExpense.getCategory(), existExpense.getExpenseTime());
            expenseDao.delete(existExpense);
            return sentEvent;
        }).orElseThrow(() -> new NotFoundExpenseException("Expense not found"));
    }
}
