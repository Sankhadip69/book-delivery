package com.springframework.bookdelivery.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TokenClaims {

    JWT_ID("jti"),
    TYPE("typ"),
    SUBJECT("sub"),
    ROLES("roles"),
    ID("id"),
    USERNAME("username"),
    EMAIL("email"),
    USER_FULL_NAME("userFullName"),
    ISSUED_AT("iat"),
    EXPIRES_AT("exp");

    private final String value;
}
