package com.sample.gateway.dto.response;

import com.sample.gateway.dto.request.ExpenseDto;
import lombok.Data;

import java.util.List;

@Data
public class ExpenseResponseSearch {
    private List<ExpenseDto> expenseDtoList;
}
