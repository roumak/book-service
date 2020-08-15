package com.this_rc.bookmicroservice.domain;

import com.this_rc.bookmicroservice.infrastructure.exceptions.NoObjectFoundException;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();

    Book getBookById(Long bookId) throws NoObjectFoundException;

    Book getBookByIsbn(String isbn) throws NoObjectFoundException;

    Book saveBook(Book newBook);

    void deleteBookById(long bookId);

    Book updateBook(Book book);

    List<Book> searchBooksByParameters(BookSearchParams request);
}
