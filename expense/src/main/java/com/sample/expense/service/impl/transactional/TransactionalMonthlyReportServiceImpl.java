package com.sample.expense.service.impl.transactional;

import com.sample.expense.dto.MonthlyReportResponseSearch;
import com.sample.expense.dto.MonthlyReportSearchDto;
import com.sample.expense.entity.MonthlyReport;
import com.sample.expense.exception.InternalExpenseException;
import com.sample.expense.exception.NotFoundMonthlyReportException;
import com.sample.expense.mapper.MonthlyReportMapper;
import com.sample.expense.repository.MonthlyReportDao;
import com.sample.expense.service.SentEventService;
import com.sample.expense.service.transactional.ReadOnlyMonthlyReportService;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class TransactionalMonthlyReportServiceImpl implements ReadOnlyMonthlyReportService {

    private final MonthlyReportDao monthlyReportDao;
    private final SentEventService sentEventService;
    private final MonthlyReportMapper monthlyReportMapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<MonthlyReport> findMonthlyReportByCategoryId(Long categoryId, LocalDateTime fromDate, LocalDateTime toDate) {
        return monthlyReportDao.findMonthlyReportByCategory_IdAndFromDateAndToDate(categoryId, fromDate, toDate);
    }

    @Transactional
    public void saveMonthlyReport(MonthlyReport monthlyReport) throws InternalExpenseException {
        monthlyReportDao.save(monthlyReport);
        sentEventService.updateSentEvent(monthlyReport);
    }

    @Transactional
    public void updateMonthlyReport(MonthlyReport monthlyReport) throws NotFoundMonthlyReportException {
        monthlyReportDao.findById(monthlyReport.getId()).ifPresentOrElse(
                existMonthlyReport -> {
                    existMonthlyReport.setCumulativeAmount(monthlyReport.getCumulativeAmount());
                    existMonthlyReport.setAlert(monthlyReport.getAlert());
                    monthlyReportDao.save(existMonthlyReport);
                    try {
                        sentEventService.updateSentEvent(monthlyReport);
                    } catch (InternalExpenseException e) {
                        log.warn("An exception occurred while updating sent event", e);
                        throw e;
                    }
                },
                () -> {
                    throw new NotFoundMonthlyReportException("Monthly Report Not Found");
                });
    }

    @Override
    @Transactional(readOnly = true)
    public MonthlyReportResponseSearch searchMonthlyReport(MonthlyReportSearchDto searchDto) {
        MonthlyReportResponseSearch monthlyReportResponseSearch = new MonthlyReportResponseSearch();
        monthlyReportResponseSearch.setMonthlyReportDtoList(monthlyReportMapper.mapToDtoList(monthlyReportDao.findAll(generateSpecification(searchDto))));
        return monthlyReportResponseSearch;
    }

    private Specification<MonthlyReport> generateSpecification(MonthlyReportSearchDto searchDto) {
        return (root, query, builder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (StringUtils.hasLength(searchDto.getCategoryName())) {
                predicateList.add(builder.equal(root.get("category_.name"), searchDto.getCategoryName()));
            }
            if (Objects.nonNull(searchDto.getCategoryId())) {
                predicateList.add(builder.equal(root.get("category_.id"), searchDto.getCategoryId()));
            }
            if (Objects.nonNull(searchDto.getCumulativeAmount())) {
                predicateList.add(builder.greaterThanOrEqualTo(root.get("cumulativeAmount"), searchDto.getCumulativeAmount()));
            }
            if (Objects.nonNull(searchDto.getFromDate())) {
                predicateList.add(builder.between(root.get("fromDate"), searchDto.getFromDate(), LocalDateTime.now()));
            }
            if (Objects.nonNull(searchDto.getToDate())) {
                predicateList.add(builder.between(root.get("toDate"), searchDto.getToDate().minusMonths(1l), searchDto.getToDate()));
            }
            return builder.and(predicateList.toArray(new Predicate[0]));
        };
    }

}
