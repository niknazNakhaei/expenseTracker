package com.sample.expense.controller;

import com.sample.expense.dto.*;
import com.sample.expense.exception.InternalExpenseException;
import com.sample.expense.exception.NotFoundExpenseException;
import com.sample.expense.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/expense")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping(path = "/save", consumes = "application/json", produces = "application/json")
    ResponseEntity<Void> save(@RequestBody ExpenseDto expenseDto) throws InternalExpenseException {
        expenseService.saveExpense(expenseDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/update", consumes = "application/json", produces = "application/json")
    ResponseEntity<Void> update(@RequestBody ExpenseUpdateDto expenseDto) throws InternalExpenseException, NotFoundExpenseException {
        expenseService.updateExpense(expenseDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/delete/{expensedId}", consumes = "application/json", produces = "application/json")
    ResponseEntity<Void> delete(@PathVariable Long expensedId) throws InternalExpenseException {
        expenseService.deleteExpense(expensedId);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/search", consumes = "application/json", produces = "application/json")
    ResponseEntity<ExpenseResponseSearch> search(@RequestBody ExpenseSearchDto searchDto) {
        return ResponseEntity.ok(expenseService.searchExpense(searchDto));
    }
}
