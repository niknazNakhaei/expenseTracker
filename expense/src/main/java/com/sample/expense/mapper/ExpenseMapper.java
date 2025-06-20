package com.sample.expense.mapper;

import com.sample.expense.dto.ExpenseDto;
import com.sample.expense.dto.ExpenseUpdateDto;
import com.sample.expense.entity.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses = {CategoryMapper.class})
public interface ExpenseMapper {

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "categoryName", source = "category.name")
    ExpenseDto mapToDto(Expense expense);

    List<ExpenseDto> mapToDtoList(List<Expense> expense);

    @Mapping(target = "category.name", source = "categoryName")
    @Mapping(target = "category.id", source = "categoryId")
    Expense mapToEntity(ExpenseDto expenseDto);

    @Mapping(target = "category.name", source = "categoryName")
    @Mapping(target = "category.id", source = "categoryId")
    Expense mapToEntity(ExpenseUpdateDto expenseDto);
}
