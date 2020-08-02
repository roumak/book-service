package com.this_rc.bookmicroservice.domain.services;

import com.this_rc.bookmicroservice.domain.BookDto;
import com.this_rc.bookmicroservice.domain.BookSearchDto;
import com.this_rc.bookmicroservice.domain.InternalBookDto;
import com.this_rc.bookmicroservice.domain.Response;
import com.this_rc.bookmicroservice.infrastructure.exceptions.NoObjectFoundException;

public interface BookService {

    Response getAllBooks();

    Response getBookById(Long bookId) throws NoObjectFoundException;

    Response getBookByIsbn(String isbn);

    Response saveBook(InternalBookDto newBook);

    Response deleteBookById(long bookId);

    Response updateBook(BookDto book);

    Response searchBooksByParameters(BookSearchDto request);
}
