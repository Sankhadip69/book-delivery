package com.springframework.bookdelivery.exception.book;

import com.springframework.bookdelivery.exception.NotFoundException;

import java.io.Serial;

public class BookNotFoundException extends NotFoundException {
    @Serial
    private static final long serialVersionUID = 6756200070542203753L;

    private static final String DEFAULT_MESSAGE =
            "The specified Book is not found!";

    private static final String MESSAGE_TEMPLATE =
            "No book found with ID: ";

    public BookNotFoundException(String id) {
        super(MESSAGE_TEMPLATE.concat(id));
    }

    public BookNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

}
