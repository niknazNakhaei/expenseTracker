package com.sample.expense.service.impl.transactional;

import com.sample.expense.repository.CategoryDao;
import com.sample.expense.service.transactional.TransactionalCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionalCategoryServiceImpl implements TransactionalCategoryService {

    private final CategoryDao categoryDao;
}
