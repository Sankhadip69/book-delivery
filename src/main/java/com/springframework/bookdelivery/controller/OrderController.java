package com.springframework.bookdelivery.controller;

import com.springframework.bookdelivery.dto.OrderDTO;
import com.springframework.bookdelivery.mapper.OrderMapper;
import com.springframework.bookdelivery.payload.request.order.CreateOrderRequest;
import com.springframework.bookdelivery.payload.request.pagination.PaginatedFindAllRequest;
import com.springframework.bookdelivery.payload.request.pagination.PaginationRequest;
import com.springframework.bookdelivery.payload.response.CustomResponse;
import com.springframework.bookdelivery.payload.response.order.OrderCreatedResponse;
import com.springframework.bookdelivery.payload.response.order.OrderGetBetweenDatesResponse;
import com.springframework.bookdelivery.payload.response.order.OrderGetByCustomerResponse;
import com.springframework.bookdelivery.payload.response.order.OrderGetResponse;
import com.springframework.bookdelivery.payload.response.pagination.CustomPageResponse;
import com.springframework.bookdelivery.service.OrderSaveService;
import com.springframework.bookdelivery.service.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class OrderController {

    private final OrderService orderService;
    private final OrderSaveService orderSaveService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CUSTOMER')")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<OrderCreatedResponse> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {

        final OrderDTO orderDTO = orderSaveService.createOrder(createOrderRequest);
        final OrderCreatedResponse response = OrderMapper.toCreatedResponse(orderDTO);
        return CustomResponse.created(response);
    }

    @GetMapping("/{orderId}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_CUSTOMER')")
    public CustomResponse<OrderGetResponse> getOrderById(@PathVariable Long orderId) {

        final OrderDTO orderDTO = orderService.findOrderById(orderId);
        final OrderGetResponse response = OrderMapper.toGetResponse(orderDTO);
        return CustomResponse.ok(response);

    }

    @GetMapping("/customer/{customerId}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_CUSTOMER')")
    public CustomResponse<CustomPageResponse<OrderGetByCustomerResponse>> getOrdersByCustomerId(
            @PathVariable Long customerId,
            @RequestBody PaginationRequest paginationRequest
    ) {

        final Page<OrderDTO> pageOfOrderDTOs = orderService
                .findAllOrdersByCustomerId(customerId, paginationRequest);

        final CustomPageResponse<OrderGetByCustomerResponse> response = OrderMapper
                .toGetByCustomerResponse(pageOfOrderDTOs);
        return CustomResponse.ok(response);

    }

    @PostMapping("/between-dates")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public CustomResponse<CustomPageResponse<OrderGetBetweenDatesResponse>> getOrdersBetweenTwoDates(
            @RequestBody PaginatedFindAllRequest paginatedFindAllRequest
    ) {
        final Page<OrderDTO> pageOfOrderDTOs = orderService
                .findAllOrdersBetweenTwoDatesAndPagination(paginatedFindAllRequest);
        final CustomPageResponse<OrderGetBetweenDatesResponse> response = OrderMapper
                .toGetBetweenDatesResponses(pageOfOrderDTOs);

        return CustomResponse.ok(response);
    }

}
