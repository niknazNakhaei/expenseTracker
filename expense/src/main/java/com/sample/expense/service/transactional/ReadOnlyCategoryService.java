package com.sample.expense.service.transactional;

import com.sample.expense.dto.CategoryDto;
import com.sample.expense.dto.CategorySearchDto;

import java.util.List;

public interface ReadOnlyCategoryService {

    List<CategoryDto> searchCategory(CategorySearchDto searchDto);

}
