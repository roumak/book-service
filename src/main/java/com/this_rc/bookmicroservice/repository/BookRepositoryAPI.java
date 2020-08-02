package com.this_rc.bookmicroservice.repository;

import com.this_rc.bookmicroservice.dto.BookSearchDto;
import com.this_rc.bookmicroservice.model.Book;
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
