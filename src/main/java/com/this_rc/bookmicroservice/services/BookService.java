package com.this_rc.bookmicroservice.services;

import com.this_rc.bookmicroservice.dto.BookSearchDto;
import com.this_rc.bookmicroservice.dto.ExternalBookDto;
import com.this_rc.bookmicroservice.dto.BookResponse;
import com.this_rc.bookmicroservice.dto.InternalBookDto;
import com.this_rc.bookmicroservice.exceptions.NoObjectFoundException;
import com.this_rc.bookmicroservice.model.Book;
import com.this_rc.bookmicroservice.repository.BookRepository;
import com.this_rc.bookmicroservice.util.ObjectConverterUtil;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class BookService {

    private BookRepository theBookRepository;

    @Autowired
    public void setBookRepository(BookRepository theBookRepository){
         this.theBookRepository=theBookRepository;
    }

    private Logger log = LoggerFactory.getLogger(BookService.class);

    public BookResponse getAllBooks() {
        List<Book> allBooks = theBookRepository.findAll();
        log.info(String.format("Retrieving all books %s", allBooks));
        List<ExternalBookDto> externalBookDtoList = ObjectConverterUtil.convertAll(Collections.unmodifiableList(allBooks),
                ExternalBookDto.class);
        return BookResponse.builder().books(externalBookDtoList).build();
    }

    public BookResponse getBookById(Long bookId) throws NoObjectFoundException {
        Optional<Book> retrievedBookOptional = theBookRepository.findById(bookId);
        ExternalBookDto externalBookDto = ObjectConverterUtil
                .convert(retrievedBookOptional
                        .orElseThrow(
                                () -> new NoObjectFoundException("No object found with this ID")), ExternalBookDto.class);
        return BookResponse.builder().book(externalBookDto).build();
    }

    public BookResponse saveBook(InternalBookDto newBook) {
        Book book = ObjectConverterUtil.convert(newBook, Book.class);
        log.info("\n" + book.toString() + "\n");
        Book outputBook = null;
        try {
            outputBook = theBookRepository.save(book);
        } catch (ConstraintViolationException e) {
            throw new ValidationException("saved data does not meet required constraints");
        }
        return BookResponse.builder().book(ObjectConverterUtil.convert(outputBook, ExternalBookDto.class)).build();
    }

    public BookResponse deleteBookById(long bookId) {
        theBookRepository.deleteById(bookId);
        return BookResponse.builder().message("book deleted").build();
    }

    public BookResponse updateBook(ExternalBookDto book) {
        log.info("updating book, bookId" + book.getBookId());
        Book addedBook = theBookRepository.saveAndFlush(ObjectConverterUtil.convert(book, Book.class));
        return BookResponse.builder().book(ObjectConverterUtil.convert(addedBook, ExternalBookDto.class)).build();
    }


    public BookResponse searchBooksByParameters(BookSearchDto request) {
//        ExampleMatcher caseInsensitiveMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        Book book=ObjectConverterUtil.convert(request,Book.class);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("authorName", ExampleMatcher.GenericPropertyMatcher::startsWith)
                .withIgnoreCase();

        Example<Book> exampleBook = Example.of(book,matcher);
              log.info("searching by example: "+request);
        Iterable<Book> result = theBookRepository.findAll(exampleBook);
        result.forEach(System.out::println);
        return null;
//        return BookResponse.builder().books(ObjectConverterUtil.convertAll(result,ExternalBookDto.class)).build();
    }
}
