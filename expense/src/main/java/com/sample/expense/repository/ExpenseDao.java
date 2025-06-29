package com.sample.expense.repository;

import com.sample.expense.entity.Expense;
import com.sample.expense.entity.projection.MonthlyExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ExpenseDao extends JpaRepository<Expense, Long>, JpaSpecificationExecutor<Expense> {

    Optional<Expense> findById(Long categoryId);

    void deleteById(Long categoryId);

    @Query(value = """
            SELECT SUM(e.AMOUNT) monthlyExpense,
                   MAX(e.EXPENSE_TIME) expenseTime,
                   e.CATEGORY_ID categoryId
            FROM TBL_EXPENSE e
            WHERE e.EXPENSE_TIME BETWEEN :fromDate AND :toDate
              AND e.CATEGORY_ID = :categoryId
            GROUP BY e.CATEGORY_ID
            """
            , nativeQuery = true)
    MonthlyExpense findMonthlyExpenseByCategoryId(@Param("categoryId") Long categoryId,
                                                  @Param("fromDate") LocalDateTime fromDate,
                                                  @Param("toDate") LocalDateTime toDate);

}
