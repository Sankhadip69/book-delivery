package com.springframework.bookdelivery.service;

import com.springframework.bookdelivery.dto.UserDTO;
import com.springframework.bookdelivery.entity.User;
import com.springframework.bookdelivery.payload.request.customer.CustomerCreateRequest;

public interface CustomerService {

    /**
     * Creates a new customer based on the provided customer creation request.
     *
     * @param customerCreateRequest The request containing customer information to be used for creation.
     * @return A {@link User} representing the newly created customer.
     */
    UserDTO createCustomer(CustomerCreateRequest customerCreateRequest);
}
