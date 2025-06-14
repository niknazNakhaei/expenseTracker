package com.sample.expense.entity.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface MonthlyExpense {

    BigDecimal getMonthlyExpense();
    Long getCategoryId();
    LocalDateTime getExpenseTime();
}
