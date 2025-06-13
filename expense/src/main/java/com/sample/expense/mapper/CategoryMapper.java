package com.sample.expense.mapper;

import com.sample.expense.dto.CategoryDto;
import com.sample.expense.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto mapToDto(Category category);

    @Mapping(target = "id", ignore = true)
    Category mapToEntity(CategoryDto dto);
}
