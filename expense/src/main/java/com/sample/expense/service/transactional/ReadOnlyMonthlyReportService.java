package com.sample.expense.service.transactional;

import com.sample.expense.dto.MonthlyReportResponseSearch;
import com.sample.expense.dto.MonthlyReportSearchDto;
import com.sample.expense.entity.MonthlyReport;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ReadOnlyMonthlyReportService {

    Optional<MonthlyReport> findMonthlyReportByCategoryId(Long categoryId, LocalDateTime fromDate, LocalDateTime toDate);

    MonthlyReportResponseSearch searchMonthlyReport(MonthlyReportSearchDto searchDto);
}
