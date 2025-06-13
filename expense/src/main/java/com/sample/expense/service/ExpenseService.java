package com.sample.expense.service;

import com.sample.expense.dto.ExpenseDto;
import com.sample.expense.exception.InternalExpenseException;
import com.sample.expense.exception.NotFoundExpenseException;
import com.sample.expense.service.transactional.ReadOnlyExpenseService;

public interface ExpenseService extends ReadOnlyExpenseService {

    void saveExpense(ExpenseDto dto) throws InternalExpenseException;

    void updateExpense(ExpenseDto dto) throws InternalExpenseException, NotFoundExpenseException;

    void deleteExpense(Long expenseId) throws InternalExpenseException;

}
