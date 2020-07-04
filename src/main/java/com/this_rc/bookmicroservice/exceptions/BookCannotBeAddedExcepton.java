package com.this_rc.bookmicroservice.exceptions;

public class BookCannotBeAddedExcepton extends RuntimeException {
    public BookCannotBeAddedExcepton(String format) {
        super(format);
    }
}
