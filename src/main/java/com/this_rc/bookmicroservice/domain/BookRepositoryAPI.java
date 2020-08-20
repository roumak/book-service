package com.this_rc.bookmicroservice.domain;

import com.this_rc.bookmicroservice.infrastructure.db.QuerySearchParams;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryAPI {

    List<BookQueryDto> getAllBooks();

    List<BookQueryDto> searchBookByParams(QuerySearchParams book);

    BookQueryDto saveBook(BookCommandDto newBook);

    void deleteBookById(long bookId);

    Optional<BookQueryDto> getById(Long bookId);

    Optional<BookQueryDto> findByBookIsbn(String bookIsbn);
}
