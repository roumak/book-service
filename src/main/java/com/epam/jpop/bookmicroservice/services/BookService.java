package com.epam.jpop.bookmicroservice.services;

import com.epam.jpop.bookmicroservice.dto.BookDto;
import com.epam.jpop.bookmicroservice.dto.BookResponse;
import com.epam.jpop.bookmicroservice.exceptions.NoObjectFoundException;
import com.epam.jpop.bookmicroservice.model.Book;
import com.epam.jpop.bookmicroservice.repository.BookRepository;
import com.epam.jpop.bookmicroservice.util.ObjectConverterUtil;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class BookService {
    @Autowired
    BookRepository theBookRepository;

    private Logger log = LoggerFactory.getLogger(BookService.class);

    public BookResponse getAllBooks() {
        List<Book> allBooks = theBookRepository.findAll();
        log.info(String.format("Retrieving all books %s" , allBooks));
        List<BookDto> bookDtoList= ObjectConverterUtil.convertAll(Collections.unmodifiableList(allBooks),
                BookDto.class);
        return BookResponse.builder().books(bookDtoList).build();
    }

    public BookResponse getBookById(Long bookId) throws NoObjectFoundException {
        Optional<Book> retrievedBookOptional = theBookRepository.findById(bookId);
        BookDto bookDto= ObjectConverterUtil
                .convert(retrievedBookOptional
                        .orElseThrow(
                                () -> new NoObjectFoundException("No object found with this ID")), BookDto.class);
        return BookResponse.builder().book(bookDto).build();
    }

    public BookResponse saveBook(BookDto newBook) {
        Book book = ObjectConverterUtil.convert(newBook, Book.class);
        log.info("\n"+book.toString()+"\n");
        Book outputBook=null;
        try {
             outputBook = theBookRepository.save(book);
        }catch (ConstraintViolationException e){
            throw new ValidationException("persistant Pojo doesnot meet required constraints");
        }
        return BookResponse.builder().book(ObjectConverterUtil.convert(outputBook, BookDto.class)).build();
    }

    public BookResponse deleteBookById(long bookId) {
        theBookRepository.deleteById(bookId);
        return BookResponse.builder().message("book deleted").build();
    }

    public BookResponse updateBook(BookDto book) {
        log.info("updating book, bookId" + book.getBookId());
        Book addedBook = theBookRepository.saveAndFlush(ObjectConverterUtil.convert(book, Book.class));
        return BookResponse.builder().book(ObjectConverterUtil.convert(addedBook, BookDto.class)).build();
    }


}
