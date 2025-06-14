package com.sample.expense.service.transactional;

import com.sample.expense.dto.ExpenseResponseSearch;
import com.sample.expense.dto.ExpenseSearchDto;

public interface ReadOnlyExpenseService {

    ExpenseResponseSearch searchExpense(ExpenseSearchDto searchDto);
}
