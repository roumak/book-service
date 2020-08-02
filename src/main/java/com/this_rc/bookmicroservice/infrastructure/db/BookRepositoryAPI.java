package com.this_rc.bookmicroservice.infrastructure.db;

import com.this_rc.bookmicroservice.domain.Book;
import com.this_rc.bookmicroservice.domain.BookSearchDto;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryAPI {

    List<Book> getAllBooks();

    List<Book> searchBookByParams(BookSearchDto book);

    Book saveBook(Book convert);

    void deleteBookById(long bookId);

    Optional<Book> findById(Long bookId);

    Optional<Book> findByBookIsbn(String bookIsbn);
}
