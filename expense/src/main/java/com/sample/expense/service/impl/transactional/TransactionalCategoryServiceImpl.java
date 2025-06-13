package com.sample.expense.service.impl.transactional;

import com.sample.expense.entity.Category;
import com.sample.expense.exception.NotFoundExpenseException;
import com.sample.expense.repository.CategoryDao;
import com.sample.expense.service.transactional.ReadOnlyCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionalCategoryServiceImpl implements ReadOnlyCategoryService {

    private final CategoryDao categoryDao;

    @Transactional
    public void saveCategory(Category category) {
        categoryDao.save(category);
    }

    @Transactional
    public void updateCategory(Category category) throws NotFoundExpenseException {
        categoryDao.findById(category.getId()).map(existCategory->{
            existCategory.setName(category.getName());
            existCategory.setDescription(category.getDescription());
            return categoryDao.save(existCategory);
        }).orElseThrow(()->new NotFoundExpenseException("Category not found"));
    }
}
