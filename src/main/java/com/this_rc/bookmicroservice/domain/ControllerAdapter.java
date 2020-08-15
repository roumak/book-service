package com.this_rc.bookmicroservice.domain;

import com.this_rc.bookmicroservice.infrastructure.controller.ControllerPort;
import com.this_rc.bookmicroservice.infrastructure.controllercdcd.RequestBookDto;
import com.this_rc.bookmicroservice.infrastructure.controller.RequestBookSearchParams;

class ControllerAdapter implements ControllerPort {
    @Override
    public Object getAllBooks() {
        return null;
    }

    @Override
    public Object getBookById(Long bookId) {
        return null;
    }

    @Override
    public Object searchBooksByParameters(RequestBookSearchParams request) {
        return null;
    }

    @Override
    public Object saveBook(RequestBookDto newBook) {
        return null;
    }

    @Override
    public Object deleteBookById(Long bookId) {
        return null;
    }

    @Override
    public Object updateBook(RequestBookDto newBook) {
        return null;
    }
}
