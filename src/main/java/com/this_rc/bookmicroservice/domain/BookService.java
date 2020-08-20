package com.this_rc.bookmicroservice.domain;


import com.this_rc.bookmicroservice.infrastructure.exceptions.NoRecordsFoundException;

import java.util.List;

interface BookService {

    List<BookQueryDto> getAllBooks();

    BookQueryDto getBookById(Long bookId) throws NoRecordsFoundException;

    BookQueryDto getBookByIsbn(String isbn) throws NoRecordsFoundException;

    BookQueryDto saveBook(BookCommandDto newBook);

    void deleteBookById(long bookId);

    BookQueryDto updateBook(BookCommandDto book);

    List<BookQueryDto> searchBooksByParameters(BookSearchParams request);
}
