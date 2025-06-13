package com.sample.expense.service;

import com.sample.expense.dto.CategoryDto;
import com.sample.expense.exception.InternalExpenseException;
import com.sample.expense.exception.NotFoundCategoryException;
import com.sample.expense.service.transactional.ReadOnlyCategoryService;

public interface CategoryService extends ReadOnlyCategoryService {

    void saveCategory(CategoryDto dto) throws InternalExpenseException;

    void updateCategory(CategoryDto dto) throws InternalExpenseException, NotFoundCategoryException;
}
