package com.this_rc.bookmicroservice.infrastructure.db;

import com.this_rc.bookmicroservice.domain.BookCommandDto;
import com.this_rc.bookmicroservice.domain.BookQueryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookRepositoryAdapter {

    BookRepository repository;

    @Autowired
    public BookRepositoryAdapter(BookRepository repository){
        this.repository=repository;
    }

    public List<BookQueryDto> getAllBooks() {
        return null;
    }

    public List<BookQueryDto> searchBookByParams(QuerySearchParams book) {
        return null;
    }

    public List<BookQueryDto> searchBookBy(String isbn, String bookTitle, String authorName, String category) {
        return null;
    }

    public BookQueryDto saveBook(BookCommandDto newBook) {
        return null;
    }

    public Optional<Book> getBookById(long bookId) {
        return Optional.empty();
    }

    public Optional<BookQueryDto> findByBookIsbn(String bookIsbn) {
        return Optional.empty();
    }

    public void deleteBookById(long id) {

    }
}
