package com.sample.gateway.dto.request;

import com.sample.gateway.dto.enumeration.CategoryType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategorySearchDto {
    private String name;
    private String userName;
    private CategoryType type;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
}
