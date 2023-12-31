package com.springframework.bookdelivery.payload.response.order;

import com.springframework.bookdelivery.dto.OrderItemDTO;
import com.springframework.bookdelivery.dto.UserDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedResponse {

    private Long id;
    private UserDTO user;
    private LocalDateTime createdAt;
    private List<OrderItemDTO> orderItems;
}
