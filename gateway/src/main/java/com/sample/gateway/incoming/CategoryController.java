package com.sample.gateway.incoming;


import com.sample.gateway.dto.request.CategoryDto;
import com.sample.gateway.dto.request.CategorySearchDto;
import com.sample.gateway.dto.request.CategoryUpdateDto;
import com.sample.gateway.dto.response.CategoryResponseSearch;
import com.sample.gateway.exception.InternalGatewayException;
import com.sample.gateway.exception.dto.ErrorResponseDto;
import com.sample.gateway.service.CategoryService;
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
@RequestMapping(path = "/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping(path = "/save", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Saving category")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))),
    })
    ResponseEntity<Void> save(@Valid @RequestBody CategoryDto categoryDto) throws InternalGatewayException {
        categoryService.saveCategory(categoryDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/update", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Updating category")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))),
    })
    ResponseEntity<Void> update(@Valid @RequestBody CategoryUpdateDto categoryDto) throws InternalGatewayException {
        categoryService.updateCategory(categoryDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/search", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Searching category")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryResponseSearch.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))),
    })
    ResponseEntity<CategoryResponseSearch> search(@Valid @RequestBody CategorySearchDto searchDto) {
        return ResponseEntity.ok(categoryService.searchCategory(searchDto));
    }
}
