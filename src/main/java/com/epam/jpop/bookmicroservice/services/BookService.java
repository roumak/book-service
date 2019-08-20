package com.epam.jpop.bookmicroservice.services;

import com.epam.jpop.bookmicroservice.dto.BookDto;
import com.epam.jpop.bookmicroservice.exceptions.NoObjectFoundException;
import com.epam.jpop.bookmicroservice.model.Book;
import com.epam.jpop.bookmicroservice.repository.BookRepository;
import com.epam.jpop.bookmicroservice.util.ObjectConverterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class BookService {
    @Autowired
    BookRepository bookRepo;

    private Logger log = LoggerFactory.getLogger(BookService.class);

    public List<BookDto> getAllBooks() {
        List<Book> allBooks = bookRepo.findAll();
        log.info("Retrieving all books", allBooks);
        return ObjectConverterUtil.convertAll(Collections.unmodifiableList(allBooks),
                BookDto.class);
    }

    public BookDto getBookById(Long bookId) throws NoObjectFoundException{
        Optional<Book> retrievedBookOptional = bookRepo.findById(bookId);

            return ObjectConverterUtil.convert(retrievedBookOptional.orElseThrow(()-> new NoObjectFoundException("No object found with this ID")), BookDto.class);

    }

    public BookDto saveBook(BookDto newBook) {
        newBook.setBookId(null);
        Book addedBook = bookRepo.saveAndFlush(ObjectConverterUtil.convert(newBook, Book.class));
        log.info("added new book to db", addedBook);
        return ObjectConverterUtil.convert(addedBook, BookDto.class);
    }

    public void deleteBookById(long bookId) throws EmptyResultDataAccessException {
        bookRepo.deleteById(bookId);
    }

    public BookDto updateBook(BookDto book) {
        log.info("updating book, bookId" + book.getBookId());
        Book addedBook = bookRepo.saveAndFlush(ObjectConverterUtil.convert(book, Book.class));
        return ObjectConverterUtil.convert(addedBook, BookDto.class);
    }


}
