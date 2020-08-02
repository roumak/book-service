package com.this_rc.bookmicroservice.infrastructure.exceptions;

public class BookAlreadyExistException extends RuntimeException {
    public BookAlreadyExistException(String user_already_exists) {
        super(user_already_exists);
    }
}
