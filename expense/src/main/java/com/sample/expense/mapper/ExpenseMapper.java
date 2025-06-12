package com.sample.expense.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {CategoryMapper.class})
public interface ExpenseMapper {

    /*@Mapping(target = "categoryId", ignore = true)
    ExpenseDto mapToDto(Expense expense);

    @Mapping(target = "category", ignore = true)
    Expense mapToEntity(ExpenseDto expenseDto);*/
}
