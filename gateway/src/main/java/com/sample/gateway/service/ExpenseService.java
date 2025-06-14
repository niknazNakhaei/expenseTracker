package com.sample.gateway.service;

import com.sample.gateway.dto.request.ExpenseDto;
import com.sample.gateway.dto.request.ExpenseSearchDto;
import com.sample.gateway.dto.request.ExpenseUpdateDto;
import com.sample.gateway.dto.response.ExpenseResponseSearch;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "expense", url = "localhost:6060/expense/expense")
public interface ExpenseService {

    @PostMapping(path = "/save", produces = "application/json")
    void saveExpense(@RequestBody ExpenseDto expenseDto);

    @PutMapping(path = "/update", produces = "application/json")
    void updateExpense(@RequestBody ExpenseUpdateDto expenseDto);

    @DeleteMapping(path = "/delete/{expensedId}", produces = "application/json")
    void deleteExpense(@PathVariable Long expensedId);

    @PostMapping(path = "/search", produces = "application/json", consumes = "application/json")
    ExpenseResponseSearch searchExpense(@RequestBody ExpenseSearchDto searchDto);
}
