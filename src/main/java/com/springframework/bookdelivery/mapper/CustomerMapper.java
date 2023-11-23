package com.springframework.bookdelivery.mapper;

import com.springframework.bookdelivery.dto.UserDTO;
import com.springframework.bookdelivery.entity.User;
import com.springframework.bookdelivery.payload.response.customer.CustomerCreatedResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerMapper {

    /**
     * Converts a {@link User} object to a {@link CustomerCreatedResponse}.
     *
     * @param source The source {@link User} object to be converted.
     * @return A {@link CustomerCreatedResponse} containing data from the source object.
     */
    public static CustomerCreatedResponse toCreatedResponse(UserDTO source) {

        if (source == null) {
            return null;
        }

        return CustomerCreatedResponse.builder()
                .id(source.getId())
                .fullName(source.getFullName())
                .username(source.getUsername())
                .email(source.getEmail())
                .build();
    }
}
