package com.sample.expense.controller;

import com.sample.expense.dto.CategoryDto;
import com.sample.expense.dto.CategoryResponseSearch;
import com.sample.expense.dto.CategorySearchDto;
import com.sample.expense.exception.InternalExpenseException;
import com.sample.expense.exception.NotFoundCategoryException;
import com.sample.expense.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping(path = "/save", consumes = "application/json", produces = "application/json")
    ResponseEntity<Void> save(@RequestBody CategoryDto categoryDto) throws InternalExpenseException {
        categoryService.saveCategory(categoryDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/update", consumes = "application/json", produces = "application/json")
    ResponseEntity<Void> update(@RequestBody CategoryDto categoryDto) throws InternalExpenseException, NotFoundCategoryException {
        categoryService.updateCategory(categoryDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/search", consumes = "application/json", produces = "application/json")
    ResponseEntity<CategoryResponseSearch> search(@RequestBody CategorySearchDto searchDto) {
        return ResponseEntity.ok(categoryService.searchCategory(searchDto));
    }
}
