package com.springframework.bookdelivery.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public abstract class ProcessException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -4150198597761563298L;

    public static final HttpStatus STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

    /**
     * Constructs a {@code ProcessException} with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     */
    protected ProcessException(String message) {
        super(message);
    }
}
