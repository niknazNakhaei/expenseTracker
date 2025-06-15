package com.sample.expense.dto;

import com.sample.expense.entity.enumeration.CategoryType;
import lombok.Data;

@Data
public class CategoryUpdateDto {
    private Long id;
    private String name;
    private String userName;
    private CategoryType type;
    private String description;
}
