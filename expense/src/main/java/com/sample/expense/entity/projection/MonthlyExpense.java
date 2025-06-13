package com.sample.expense.entity.projection;

import java.math.BigDecimal;

public interface MonthlyExpense {

    BigDecimal getMonthlyExpense();
    Long getCategoryId();
}
