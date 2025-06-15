package com.sample.expense.service.impl.transactional;

import com.sample.expense.dto.CategoryResponseSearch;
import com.sample.expense.dto.CategorySearchDto;
import com.sample.expense.entity.Category;
import com.sample.expense.exception.NotFoundExpenseException;
import com.sample.expense.mapper.CategoryMapper;
import com.sample.expense.repository.CategoryDao;
import com.sample.expense.service.transactional.ReadOnlyCategoryService;
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
public class TransactionalCategoryServiceImpl implements ReadOnlyCategoryService {

    private final CategoryDao categoryDao;
    private final CategoryMapper mapper;

    @Transactional
    public void saveCategory(Category category) {
        categoryDao.save(category);
    }

    @Transactional
    public void updateCategory(Category category) throws NotFoundExpenseException {
        categoryDao.findById(category.getId()).map(existCategory -> {
            existCategory.setName(category.getName());
            existCategory.setDescription(category.getDescription());
            return existCategory;
        }).orElseThrow(() -> new NotFoundExpenseException("Category not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponseSearch searchCategory(CategorySearchDto searchDto) {
        CategoryResponseSearch categoryResponseSearch = new CategoryResponseSearch();
        categoryResponseSearch.setCategoryDtoList(mapper.mapToDtoList(categoryDao.findAll(generateSpecification(searchDto))));
        return categoryResponseSearch;
    }

    private Specification<Category> generateSpecification(CategorySearchDto searchDto) {

        return (root, query, builder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (StringUtils.hasLength(searchDto.getName())) {
                predicateList.add(builder.like(root.get("name"), "%" + searchDto.getName() + "%"));
            }
            if (Objects.nonNull(searchDto.getUserId())) {
                predicateList.add(builder.equal(root.get("userId"), searchDto.getUserId()));
            }
            if (Objects.nonNull(searchDto.getType())) {
                predicateList.add(builder.equal(root.get("type"), searchDto.getType()));
            }
            if (Objects.nonNull(searchDto.getFromDate()) || Objects.nonNull(searchDto.getToDate())) {
                if (Objects.isNull(searchDto.getFromDate())) {
                    predicateList.add(builder.between(root.get("createdTime"), searchDto.getToDate().minusMonths(1L), searchDto.getToDate()));
                }
                if (Objects.isNull(searchDto.getToDate())) {
                    predicateList.add(builder.between(root.get("createdTime"), searchDto.getFromDate(), LocalDateTime.now()));
                } else {
                    predicateList.add(builder.between(root.get("createdTime"), searchDto.getFromDate(), searchDto.getToDate()));
                }
            }
            return builder.and(predicateList.toArray(new Predicate[0]));
        };
    }
}
