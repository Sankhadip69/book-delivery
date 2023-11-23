package com.springframework.bookdelivery.mapper;

import com.springframework.bookdelivery.dto.UserDTO;
import com.springframework.bookdelivery.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    /**
     * Converts a {@link User} object to a {@link UserDTO}.
     *
     * @param user The {@link User} object to be converted.
     * @return A {@link UserDTO} containing data from the source user object.
     */
    public static UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .username(user.getUsername())
                .build();
    }

    /**
     * Converts a {@link UserDTO} object to a {@link User} entity.
     *
     * @param userDTO The {@link UserDTO} object to be converted.
     * @return A {@link User} entity containing data from the source DTO.
     */
    public static User toUser(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .fullName(userDTO.getFullName())
                .build();
    }
}
