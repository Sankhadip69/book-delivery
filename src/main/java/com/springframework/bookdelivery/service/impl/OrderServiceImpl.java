package com.springframework.bookdelivery.service.impl;

import com.springframework.bookdelivery.dto.OrderDTO;
import com.springframework.bookdelivery.enums.Role;
import com.springframework.bookdelivery.exception.order.OrderNotFoundException;
import com.springframework.bookdelivery.mapper.OrderMapper;
import com.springframework.bookdelivery.payload.request.pagination.DateIntervalRequest;
import com.springframework.bookdelivery.payload.request.pagination.PaginatedFindAllRequest;
import com.springframework.bookdelivery.payload.request.pagination.PaginationRequest;
import com.springframework.bookdelivery.repository.OrderRepository;
import com.springframework.bookdelivery.security.CustomUserDetails;
import com.springframework.bookdelivery.service.OrderService;
import com.springframework.bookdelivery.util.Identity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final Identity identity;

    /**
     * Retrieves an order by its unique identifier.
     *
     * @param id The unique identifier of the order.
     * @return An {@link OrderDTO} representing the order with the specified ID.
     */
    @Override
    public OrderDTO findOrderById(Long id) {

        CustomUserDetails userDetails = identity.getCustomUserDetails();

        return orderRepository.findById(id)
                .map(order -> {
                    // Check access based on customUserDetails here
                    if ((userDetails.getId().equals(order.getUser().getId()) &&
                            userDetails.getUser().getRole().equals(Role.ROLE_CUSTOMER))
                            || userDetails.getUser().getRole().equals(Role.ROLE_ADMIN)) {
                        return OrderMapper.toOrderDTO(order);
                    } else {
                        throw new AccessDeniedException("You cannot access this order by Id");
                    }
                })
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));
    }

    /**
     * Retrieves a paginated list of all orders associated with a customer based on their unique identifier.
     *
     * @param customerId The unique identifier of the customer.
     * @param paginationRequest The request containing pagination information.
     * @return A {@link Page} of {@link OrderDTO} objects representing the list of orders for the customer.
     */
    @Override
    public Page<OrderDTO> findAllOrdersByCustomerId(Long customerId, PaginationRequest paginationRequest) {

        final CustomUserDetails userDetails = identity.getCustomUserDetails();
        final Role userRole = userDetails.getUser().getRole();
        if ((userRole.equals(Role.ROLE_CUSTOMER) && userDetails.getId().equals(customerId))
                || userRole.equals(Role.ROLE_ADMIN)) {
            return orderRepository.findAllByUserId(customerId, paginationRequest.toPageable())
                    .map(OrderMapper::toOrderDTO);
        }

        throw new AccessDeniedException("You cannot access order statistics");

    }

    /**
     * Retrieves a paginated list of all orders within a specified date interval.
     *
     * @param paginatedFindAllRequest The request containing date interval and pagination information.
     * @return A {@link Page} of {@link OrderDTO} objects representing the list of orders within the specified date interval.
     */
    @Override
    public Page<OrderDTO> findAllOrdersBetweenTwoDatesAndPagination(PaginatedFindAllRequest paginatedFindAllRequest) {

        DateIntervalRequest dateIntervalRequest = paginatedFindAllRequest.getDateIntervalRequest();
        PaginationRequest paginationRequest = paginatedFindAllRequest.getPaginationRequest();

        return orderRepository.findAllByCreatedAtBetween(
                        dateIntervalRequest.getStartDate(),
                        dateIntervalRequest.getEndDate(),
                        paginationRequest.toPageable())
                .map(OrderMapper::toOrderDTO);

    }

}
