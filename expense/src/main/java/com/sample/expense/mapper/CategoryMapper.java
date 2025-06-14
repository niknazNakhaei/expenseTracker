package com.sample.expense.mapper;

import com.sample.expense.dto.CategoryDto;
import com.sample.expense.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto mapToDto(Category category);

    List<CategoryDto> mapToDtoList(List<Category> categoryList);

    @Mapping(target = "id", ignore = true)
    Category mapToEntity(CategoryDto dto);
}
