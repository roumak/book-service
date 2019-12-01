package com.epam.jpop.bookmicroservice.exceptions;

public class BookCannotBeAddedExcepton extends RuntimeException {
    public BookCannotBeAddedExcepton(String format) {
        super(format);
    }
}
