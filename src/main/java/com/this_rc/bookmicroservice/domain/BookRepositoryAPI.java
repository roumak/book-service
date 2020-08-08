package com.this_rc.bookmicroservice.domain;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryAPI {

    List<Book> getAllBooks();

    List<Book> searchBookByParams(BookSearchParams book);

    Book saveBook(Book convert);

    void deleteBookById(long bookId);

    Optional<Book> findById(Long bookId);

    Optional<Book> findByBookIsbn(String bookIsbn);
}
