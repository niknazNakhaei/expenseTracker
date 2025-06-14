package com.sample.gateway.dto.request;

import com.sample.gateway.dto.enumeration.CategoryType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryDto {
    @NotBlank
    private String name;
    @NotNull
    private Long userId;
    @NotNull
    private CategoryType type;
    private String description;
}
