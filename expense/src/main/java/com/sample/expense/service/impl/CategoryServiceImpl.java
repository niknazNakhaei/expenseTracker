package com.sample.expense.service.impl;

import com.sample.expense.dto.CategoryDto;
import com.sample.expense.entity.Category;
import com.sample.expense.exception.InternalExpenseException;
import com.sample.expense.exception.NotFoundCategoryException;
import com.sample.expense.mapper.CategoryMapper;
import com.sample.expense.service.CategoryService;
import com.sample.expense.service.impl.transactional.TransactionalCategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final TransactionalCategoryServiceImpl categoryService;
    private final CategoryMapper mapper;

    @Override
    public void saveCategory(CategoryDto dto) throws InternalExpenseException {
        try {
            log.info("Try to save category: {}", dto.getName());
            Category category=mapper.mapToEntity(dto);
            categoryService.saveCategory(category);
        } catch (Exception e) {
            throw new InternalExpenseException(e.getMessage());
        }
    }

    @Override
    public void updateCategory(CategoryDto dto) throws InternalExpenseException, NotFoundCategoryException {
        try {
            log.info("Try to update category: {}", dto.getName());
            Category category=mapper.mapToEntity(dto);
            categoryService.updateCategory(category);
        } catch (NotFoundCategoryException e) {
            log.warn("Category not found: {}", dto.getName());
        } catch (Exception e) {
            throw new InternalExpenseException(e.getMessage(), e);
        }
    }
}
