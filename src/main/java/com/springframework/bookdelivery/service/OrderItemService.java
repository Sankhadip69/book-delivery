package com.springframework.bookdelivery.service;

import com.springframework.bookdelivery.dto.OrderItemDTO;
import com.springframework.bookdelivery.payload.request.order.OrderItemRequest;

public interface OrderItemService {

    /**
     * Creates a new order item based on the provided order item request.
     *
     * @param orderDetailRequest The request containing order item information to be used for creation.
     * @return An {@link OrderItemDTO} representing the newly created order item.
     */
    OrderItemDTO createOrderItem(OrderItemRequest orderDetailRequest);

}
