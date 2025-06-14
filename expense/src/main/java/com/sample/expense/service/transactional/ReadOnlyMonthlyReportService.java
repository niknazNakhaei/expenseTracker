package com.sample.expense.service.transactional;

import com.sample.expense.dto.MonthlyReportDto;
import com.sample.expense.dto.MonthlyReportSearchDto;
import com.sample.expense.entity.MonthlyReport;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReadOnlyMonthlyReportService {

    Optional<MonthlyReport> findMonthlyReportByCategoryId(Long categoryId, LocalDateTime fromDate, LocalDateTime toDate);

    List<MonthlyReportDto> searchMonthlyReport(MonthlyReportSearchDto searchDto);
}
