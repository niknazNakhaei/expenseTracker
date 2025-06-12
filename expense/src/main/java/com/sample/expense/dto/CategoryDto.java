package com.sample.expense.dto;

import lombok.Data;

@Data
public class CategoryDto {
    private String name;
    private Long userId;
    private Long parentId;
    private String description;
}
