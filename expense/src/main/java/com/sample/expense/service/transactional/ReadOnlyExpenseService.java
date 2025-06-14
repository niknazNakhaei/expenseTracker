package com.sample.expense.service.transactional;

import com.sample.expense.dto.ExpenseDto;
import com.sample.expense.dto.ExpenseSearchDto;

import java.util.List;

public interface ReadOnlyExpenseService {

    List<ExpenseDto> searchExpense(ExpenseSearchDto searchDto);
}
