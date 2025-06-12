package com.sample.expense.repository;

import com.sample.expense.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseDao extends JpaRepository<Expense, Long> {
}
