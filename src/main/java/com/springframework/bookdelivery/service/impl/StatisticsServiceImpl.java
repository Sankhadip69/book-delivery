package com.springframework.bookdelivery.service.impl;

import com.springframework.bookdelivery.dto.OrderReportDTO;
import com.springframework.bookdelivery.enums.Role;
import com.springframework.bookdelivery.payload.request.pagination.PaginationRequest;
import com.springframework.bookdelivery.repository.OrderRepository;
import com.springframework.bookdelivery.security.CustomUserDetails;
import com.springframework.bookdelivery.service.StatisticsService;
import com.springframework.bookdelivery.util.Identity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {


    private final OrderRepository orderRepository;

    private final Identity identity;

    /**
     * Retrieves order statistics for a specific customer.
     *
     * @param customerId        The unique identifier of the customer.
     * @param paginationRequest The request containing pagination information.
     * @return A {@link Page} of {@link OrderReportDTO} objects representing order statistics for the customer.
     */
    @Override
    public Page<OrderReportDTO> getOrderStatisticsByCustomerId(Long customerId, PaginationRequest paginationRequest) {

        final CustomUserDetails userDetails = identity.getCustomUserDetails();
        final Role userRole = userDetails.getUser().getRole();
        if ((userRole.equals(Role.ROLE_CUSTOMER) && userDetails.getId().equals(customerId))
                || userRole.equals(Role.ROLE_ADMIN)) {
            return orderRepository.findOrderStatisticsByCustomerId(customerId, paginationRequest.toPageable());
        }
        throw new AccessDeniedException("You cannot access order statistics");
    }

    /**
     * Retrieves overall order statistics.
     *
     * @param paginationRequest The request containing pagination information.
     * @return A {@link Page} of {@link OrderReportDTO} objects representing overall order statistics.
     */
    @Override
    public Page<OrderReportDTO> getAllOrderStatistics(PaginationRequest paginationRequest) {
        return orderRepository.findAllOrderStatistics(paginationRequest.toPageable());
    }

}
