package com.sample.gateway.dto.response;

import com.sample.gateway.dto.request.CategoryDto;
import lombok.Data;

import java.util.List;

@Data
public class CategoryResponseSearch {
    private List<CategoryDto> categoryDtoList;
}
