package com.springframework.bookdelivery.controller;

import com.springframework.bookdelivery.dto.OrderReportDTO;
import com.springframework.bookdelivery.mapper.OrderReportMapper;
import com.springframework.bookdelivery.payload.request.pagination.PaginationRequest;
import com.springframework.bookdelivery.payload.response.CustomResponse;
import com.springframework.bookdelivery.payload.response.order.OrderReportResponse;
import com.springframework.bookdelivery.payload.response.pagination.CustomPageResponse;
import com.springframework.bookdelivery.service.StatisticsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/statistics")
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/{customerId}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_CUSTOMER')")
    public CustomResponse<CustomPageResponse<OrderReportResponse>> getOrderStatisticsByCustomerId(
            @PathVariable Long customerId,
            @RequestBody PaginationRequest paginationRequest
    ) {
        Page<OrderReportDTO> orderReportDTOs = statisticsService
                .getOrderStatisticsByCustomerId(customerId, paginationRequest);
        CustomPageResponse<OrderReportResponse> orderReportResponse = OrderReportMapper.toOrderReportResponseList(orderReportDTOs);
        return CustomResponse.ok(orderReportResponse);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public CustomResponse<CustomPageResponse<OrderReportResponse>> getAllOrderStatistics(
            @RequestBody PaginationRequest paginationRequest
    ) {
        Page<OrderReportDTO> orderReportDTOs = statisticsService.getAllOrderStatistics(paginationRequest);
        CustomPageResponse<OrderReportResponse> orderReportResponse = OrderReportMapper.toOrderReportResponseList(orderReportDTOs);
        return CustomResponse.ok(orderReportResponse);
    }

}
