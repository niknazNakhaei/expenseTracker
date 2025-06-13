package com.sample.expense.repository;

import com.sample.expense.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpenseDao extends JpaRepository<Expense, Long> {

    Optional<Expense> findById(Long categoryId);

    void deleteById(Long categoryId);
}
