package com.this_rc.bookmicroservice.infrastructure.exceptions;

public class BookCannotBeAddedExcepton extends RuntimeException {
    public BookCannotBeAddedExcepton(String format) {
        super(format);
    }
}
