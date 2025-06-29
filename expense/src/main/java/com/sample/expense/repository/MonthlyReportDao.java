package com.sample.expense.repository;

import com.sample.expense.entity.MonthlyReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.Optional;

public interface MonthlyReportDao extends JpaRepository<MonthlyReport, Long>, JpaSpecificationExecutor<MonthlyReport> {

    Optional<MonthlyReport> findMonthlyReportByCategory_IdAndFromDateAndToDateLessThanEqual(Long categoryId, LocalDate fromDate, LocalDate toDate);

}
