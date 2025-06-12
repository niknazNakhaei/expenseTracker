package com.sample.expense.service.impl.transactional;

import com.sample.expense.repository.ExpenseDao;
import com.sample.expense.service.transactional.TransactionalExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionalExpenseServiceImpl implements TransactionalExpenseService {

    private final ExpenseDao expenseDao;
}
