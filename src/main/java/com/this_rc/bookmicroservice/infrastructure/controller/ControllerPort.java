package com.this_rc.bookmicroservice.infrastructure.controller;

public interface ControllerPort {
    Object getAllBooks();

    Object getBookById(Long bookId);

    Object searchBooksByParameters(RequestBookSearchParams request);

    Object saveBook(RequestBookDto newBook);

    Object deleteBookById(Long bookId);

    Object updateBook(RequestBookDto newBook);
}
