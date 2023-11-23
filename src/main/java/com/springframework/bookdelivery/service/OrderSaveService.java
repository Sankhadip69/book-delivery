package com.springframework.bookdelivery.service;

import com.springframework.bookdelivery.dto.OrderDTO;
import com.springframework.bookdelivery.payload.request.order.CreateOrderRequest;

public interface OrderSaveService {

    /**
     * Creates a new order based on the provided create order request.
     *
     * @param createOrderRequest The request containing order information to be used for creation.
     * @return An {@link OrderDTO} representing the newly created order.
     */
    OrderDTO createOrder(CreateOrderRequest createOrderRequest);
}
