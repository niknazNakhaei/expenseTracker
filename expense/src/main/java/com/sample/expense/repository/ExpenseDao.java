package com.sample.expense.repository;

import com.sample.expense.entity.Expense;
import com.sample.expense.entity.projection.MonthlyExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ExpenseDao extends JpaRepository<Expense, Long> {

    Optional<Expense> findById(Long categoryId);

    void deleteById(Long categoryId);

    @Query(value = """
            SELECT SUM(e.AMOUNT) monthlyAmount,
                   e.CATEGORY_ID category
            FROM TBL_EXPENSE e
            WHERE CREATED_TIME BETWEEN :fromDate AND :toDate
              AND e.USER_ID = :userId
              AND e.CATEGORY_ID = :categoryId
            GROUP BY e.CATEGORY_ID
            """
            , nativeQuery = true)
    MonthlyExpense findMonthlyExpenseByCategoryIdAndUserId(@Param("categoryId") Long categoryId,
                                                           @Param("userId") Long userId, @Param("fromDate") LocalDateTime fromDate,
                                                           @Param("toDate") LocalDateTime toDate);

}
