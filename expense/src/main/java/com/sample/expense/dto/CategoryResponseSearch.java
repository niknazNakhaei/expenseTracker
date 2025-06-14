package com.sample.expense.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryResponseSearch {
    private List<CategoryDto> categoryDtoList;
}
