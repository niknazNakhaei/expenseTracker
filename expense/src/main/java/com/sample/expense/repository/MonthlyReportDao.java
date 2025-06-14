package com.sample.expense.repository;

import com.sample.expense.entity.MonthlyReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface MonthlyReportDao extends JpaRepository<MonthlyReport, Long> {

    Optional<MonthlyReport> findMonthlyReportByCategory_IdAndFromDateAndToDate(Long categoryId, LocalDateTime fromDate, LocalDateTime toDate);


}
