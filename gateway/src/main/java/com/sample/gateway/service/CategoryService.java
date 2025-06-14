package com.sample.gateway.service;

import com.sample.gateway.dto.request.CategoryDto;
import com.sample.gateway.dto.request.CategorySearchDto;
import com.sample.gateway.dto.request.CategoryUpdateDto;
import com.sample.gateway.dto.response.CategoryResponseSearch;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "category", url = "localhost:6060/expense/category")
public interface CategoryService {

    @PostMapping(path = "/save", produces = "application/json")
    void saveCategory(@RequestBody CategoryDto categoryDto);

    @PutMapping(path = "/update", produces = "application/json")
    void updateCategory(@RequestBody CategoryUpdateDto categoryDto);

    @PostMapping(path = "/search", produces = "application/json", consumes = "application/json")
    CategoryResponseSearch searchCategory(@RequestBody CategorySearchDto searchDto);
}
