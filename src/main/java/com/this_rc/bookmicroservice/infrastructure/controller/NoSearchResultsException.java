package com.this_rc.bookmicroservice.infrastructure.controller;

public class NoSearchResultsException extends RuntimeException {
    public NoSearchResultsException(String message) {
        super(message);
    }
}
