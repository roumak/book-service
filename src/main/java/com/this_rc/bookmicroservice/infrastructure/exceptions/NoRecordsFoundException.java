package com.this_rc.bookmicroservice.infrastructure.exceptions;

public class NoRecordsFoundException extends RuntimeException {
    public NoRecordsFoundException(String s) {
        super(s);
    }
}
