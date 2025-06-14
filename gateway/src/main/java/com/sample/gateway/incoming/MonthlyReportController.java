package com.sample.gateway.incoming;


import com.sample.gateway.dto.request.MonthlyReportSearchDto;
import com.sample.gateway.dto.response.MonthlyReportResponseSearch;
import com.sample.gateway.exception.dto.ErrorResponseDto;
import com.sample.gateway.service.MonthlyReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/monthly_report")
@RequiredArgsConstructor
public class MonthlyReportController {

    private final MonthlyReportService monthlyReportService;

    @PostMapping(path = "/search", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Searching monthly report")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MonthlyReportResponseSearch.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))),
    })
    ResponseEntity<MonthlyReportResponseSearch> search(@Valid @RequestBody MonthlyReportSearchDto searchDto) {
        return ResponseEntity.ok(monthlyReportService.searchMonthlyReport(searchDto));
    }
}
