package com.sample.expense.service.transactional;

import com.sample.expense.dto.CategoryResponseSearch;
import com.sample.expense.dto.CategorySearchDto;

public interface ReadOnlyCategoryService {

    CategoryResponseSearch searchCategory(CategorySearchDto searchDto);

}
