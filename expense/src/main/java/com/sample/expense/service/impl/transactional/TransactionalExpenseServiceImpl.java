package com.sample.expense.service.impl.transactional;

import com.sample.expense.dto.ExpenseResponseSearch;
import com.sample.expense.dto.ExpenseSearchDto;
import com.sample.expense.entity.Expense;
import com.sample.expense.entity.SentEvent;
import com.sample.expense.exception.NotFoundExpenseException;
import com.sample.expense.mapper.ExpenseMapper;
import com.sample.expense.repository.ExpenseDao;
import com.sample.expense.service.SentEventService;
import com.sample.expense.service.transactional.ReadOnlyExpenseService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TransactionalExpenseServiceImpl implements ReadOnlyExpenseService {

    private final ExpenseDao expenseDao;
    private final SentEventService sentEventService;
    private final ExpenseMapper expenseMapper;

    @Transactional
    public SentEvent saveExpense(Expense expense) {
        expenseDao.save(expense);
        return sentEventService.saveSentEvent(expense.getCategory(), expense.getExpenseTime());
    }

    @Transactional
    public SentEvent updateExpense(Expense expense) {
        return expenseDao.findById(expense.getId()).map(existExpense -> {
            existExpense.setAmount(expense.getAmount());
            existExpense.setDescription(expense.getDescription());
            existExpense.setExpenseTime(expense.getExpenseTime());
            expenseDao.save(existExpense);
            return sentEventService.saveSentEvent(expense.getCategory(), expense.getExpenseTime());
        }).orElseThrow(() -> new NotFoundExpenseException("Expense not found"));
    }

    @Transactional
    public SentEvent deleteExpense(Long expenseId) {
        return expenseDao.findById(expenseId).map(existExpense -> {
            SentEvent sentEvent = sentEventService.saveSentEvent(existExpense.getCategory(), existExpense.getExpenseTime());
            expenseDao.delete(existExpense);
            return sentEvent;
        }).orElseThrow(() -> new NotFoundExpenseException("Expense not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public ExpenseResponseSearch searchExpense(ExpenseSearchDto searchDto) {
        ExpenseResponseSearch expenseResponseSearch = new ExpenseResponseSearch();
        expenseResponseSearch.setExpenseDtoList(expenseMapper.mapToDtoList(expenseDao.findAll(generateSpecification(searchDto))));
        return expenseResponseSearch;
    }

    private Specification<Expense> generateSpecification(ExpenseSearchDto searchDto) {

        return (root, query, builder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (StringUtils.hasLength(searchDto.getCategoryName())) {
                predicateList.add(builder.equal(root.get("category_.name"), searchDto.getCategoryName()));
            }
            if (Objects.nonNull(searchDto.getCategoryId())) {
                predicateList.add(builder.equal(root.get("category_.id"), searchDto.getCategoryId()));
            }
            if (Objects.nonNull(searchDto.getAmount())) {
                predicateList.add(builder.greaterThanOrEqualTo(root.get("amount"), searchDto.getAmount()));
            }
            if (Objects.nonNull(searchDto.getExpenseFromDate()) || Objects.nonNull(searchDto.getExpenseToDate())) {
                if (Objects.isNull(searchDto.getExpenseFromDate())) {
                    predicateList.add(builder.between(root.get("expenseTime"), searchDto.getExpenseToDate().minusMonths(1L), searchDto.getExpenseToDate()));
                }
                if (Objects.isNull(searchDto.getExpenseToDate())) {
                    predicateList.add(builder.between(root.get("expenseTime"), searchDto.getExpenseFromDate(), LocalDateTime.now()));
                } else {
                    predicateList.add(builder.between(root.get("expenseTime"), searchDto.getExpenseFromDate(), searchDto.getExpenseToDate()));
                }
            }
            return builder.and(predicateList.toArray(new Predicate[0]));
        };
    }
}
