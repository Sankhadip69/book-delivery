package com.springframework.bookdelivery.controller;

import com.springframework.bookdelivery.dto.UserDTO;
import com.springframework.bookdelivery.mapper.CustomerMapper;
import com.springframework.bookdelivery.payload.request.customer.CustomerCreateRequest;
import com.springframework.bookdelivery.payload.response.CustomResponse;
import com.springframework.bookdelivery.payload.response.customer.CustomerCreatedResponse;
import com.springframework.bookdelivery.service.CustomerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<CustomerCreatedResponse> createCustomer(
            @RequestBody @Valid final CustomerCreateRequest customerCreateRequest
            ) {
        final UserDTO createdUser = customerService.createCustomer(customerCreateRequest);
        final CustomerCreatedResponse createdResponse = CustomerMapper.toCreatedResponse(createdUser);
        return CustomResponse.created(createdResponse);
    }
}
