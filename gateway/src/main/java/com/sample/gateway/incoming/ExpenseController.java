package com.sample.gateway.incoming;


import com.sample.gateway.dto.request.ExpenseDto;
import com.sample.gateway.dto.request.ExpenseSearchDto;
import com.sample.gateway.dto.request.ExpenseUpdateDto;
import com.sample.gateway.dto.response.ExpenseResponseSearch;
import com.sample.gateway.exception.InternalGatewayException;
import com.sample.gateway.exception.dto.ErrorResponseDto;
import com.sample.gateway.service.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/expense")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping(path = "/save", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Saving expense")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))),
    })
    ResponseEntity<Void> save(@Valid @RequestBody ExpenseDto expenseDto) throws InternalGatewayException {
        expenseService.saveExpense(expenseDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/update", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Updating expense")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))),
    })
    ResponseEntity<Void> update(@Valid @RequestBody ExpenseUpdateDto expenseDto) throws InternalGatewayException {
        expenseService.updateExpense(expenseDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/delete/{expensedId}")
    @Operation(summary = "Deleting expense")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))),
    })
    ResponseEntity<Void> update(@Valid @PathVariable Long expensedId) throws InternalGatewayException {
        expenseService.deleteExpense(expensedId);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/search", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Searching expense")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExpenseResponseSearch.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))),
    })
    ResponseEntity<ExpenseResponseSearch> search(@Valid @RequestBody ExpenseSearchDto searchDto) {
        return ResponseEntity.ok(expenseService.searchExpense(searchDto));
    }
}
