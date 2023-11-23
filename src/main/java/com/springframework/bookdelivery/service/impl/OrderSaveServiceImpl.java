package com.springframework.bookdelivery.service.impl;

import com.springframework.bookdelivery.dto.OrderDTO;
import com.springframework.bookdelivery.dto.OrderItemDTO;
import com.springframework.bookdelivery.entity.Order;
import com.springframework.bookdelivery.entity.User;
import com.springframework.bookdelivery.exception.user.UserNotFoundException;
import com.springframework.bookdelivery.mapper.OrderItemMapper;
import com.springframework.bookdelivery.mapper.OrderMapper;
import com.springframework.bookdelivery.payload.request.order.CreateOrderRequest;
import com.springframework.bookdelivery.repository.OrderRepository;
import com.springframework.bookdelivery.security.CustomUserDetails;
import com.springframework.bookdelivery.service.OrderItemService;
import com.springframework.bookdelivery.service.OrderSaveService;
import com.springframework.bookdelivery.service.UserService;
import com.springframework.bookdelivery.util.Identity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderSaveServiceImpl implements OrderSaveService {

    private final OrderItemService orderItemService;

    private final UserService userService;

    private final OrderRepository orderRepository;

    private final Identity identity;

    /**
     * Creates a new order based on the provided create order request.
     *
     * @param createOrderRequest The request containing order information to be used for creation.
     * @return An {@link OrderDTO} representing the newly created order.
     */
    @Transactional
    @Override
    public OrderDTO createOrder(CreateOrderRequest createOrderRequest) {

        CustomUserDetails customUserDetails = identity.getCustomUserDetails();

        User user = userService.findByEmail(customUserDetails.getEmail())
                .orElseThrow(() -> new UserNotFoundException(customUserDetails.getId()));

        List<OrderItemDTO> orderItemDTOs = createOrderRequest
                .getOrderDetailSet()
                .stream()
                .map(orderItemService::createOrderItem)
                .toList();

        Order order = Order.builder()
                .user(user)
                .build();

        order.setOrderItems(OrderItemMapper.toOrderItem(orderItemDTOs));

        return OrderMapper.toOrderDTO(orderRepository.save(order));

    }

}
