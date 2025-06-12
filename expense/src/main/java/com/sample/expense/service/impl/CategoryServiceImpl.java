package com.sample.expense.service.impl;

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
}
