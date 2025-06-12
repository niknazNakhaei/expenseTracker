package com.sample.expense.mapper;

import com.sample.expense.dto.CategoryDto;
import com.sample.expense.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "parentId", ignore = true)
    CategoryDto mapToDto(Category category);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdTime", ignore = true)
    @Mapping(target = "parent", ignore = true)
    Category mapToEntity(CategoryDto dto);
}
