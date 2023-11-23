package com.springframework.bookdelivery.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public abstract class AlreadyException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -7986483342718835320L;

    public static final HttpStatus STATUS = HttpStatus.CONFLICT;

    /**
     * Constructs an {@code AlreadyException} with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     */
    protected AlreadyException(String message) {
        super(message);
    }
}
