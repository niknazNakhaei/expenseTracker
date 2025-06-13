package com.sample.expense.repository;

import com.sample.expense.entity.MonthlyReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlyReportDao extends JpaRepository<MonthlyReport, Long> {
}
