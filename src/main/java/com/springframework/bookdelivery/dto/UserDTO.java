package com.springframework.bookdelivery.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class UserDTO {

    private Long id;
    private String fullName;
    private String username;
    private String email;
}
