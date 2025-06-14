package com.sample.expense.dto;

import com.sample.expense.entity.enumeration.CategoryType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategorySearchDto {
    private String name;
    private Long userId;
    private CategoryType type;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
}
