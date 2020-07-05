package com.this_rc.bookmicroservice.services;

import com.this_rc.bookmicroservice.dto.BookSearchDto;
import com.this_rc.bookmicroservice.dto.BookDto;
import com.this_rc.bookmicroservice.dto.Response;
import com.this_rc.bookmicroservice.dto.InternalBookDto;
import com.this_rc.bookmicroservice.exceptions.NoObjectFoundException;
import com.this_rc.bookmicroservice.model.Book;
import com.this_rc.bookmicroservice.repository.BookRepository;
import com.this_rc.bookmicroservice.util.ObjectConverterUtil;
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

    private BookRepository theBookRepository;

    @Autowired
    public void setBookRepository(BookRepository theBookRepository){
         this.theBookRepository=theBookRepository;
    }

    private Logger log = LoggerFactory.getLogger(BookService.class);

    public Response getAllBooks() {
        List<Book> allBooks = theBookRepository.findAll();
        log.info(String.format("Retrieving all books %s", allBooks));
        List<BookDto> bookDtoList = ObjectConverterUtil.convertAll(Collections.unmodifiableList(allBooks),
                BookDto.class);
        return Response.builder().books(bookDtoList).build();
    }

    public Response getBookById(Long bookId) throws NoObjectFoundException {
        Optional<Book> retrievedBookOptional = theBookRepository.findById(bookId);
        BookDto bookDto = ObjectConverterUtil
                .convert(retrievedBookOptional
                        .orElseThrow(
                                () -> new NoObjectFoundException("No object found with this ID")), BookDto.class);
        return Response.builder().book(bookDto).build();
    }

    public Response getBookByIsbn(Long isbn){
        Book book=theBookRepository.findByIsbn(isbn);

        return Response.builder()
                .book(ObjectConverterUtil.convert(book,BookDto.class))
                .build();
    }

    public Response saveBook(InternalBookDto newBook) {
        Book book = ObjectConverterUtil.convert(newBook, Book.class);
        log.info("\n" + book.toString() + "\n");
        Book outputBook = null;
        try {
            outputBook = theBookRepository.save(book);
        } catch (ConstraintViolationException e) {
            throw new ValidationException("saved data does not meet required constraints");
        }
        return Response.builder().book(ObjectConverterUtil.convert(outputBook, BookDto.class)).build();
    }

    public Response deleteBookById(long bookId) {
        theBookRepository.deleteById(bookId);
        return Response.builder().message("book deleted").build();
    }

    public Response updateBook(BookDto book) {
        log.info("updating book, bookId" + book.getBookId());
        Book addedBook = theBookRepository.saveAndFlush(ObjectConverterUtil.convert(book, Book.class));
        return Response.builder().book(ObjectConverterUtil.convert(addedBook, BookDto.class)).build();
    }


    public Response searchBooksByParameters(BookSearchDto request) {
        List<Book> books=theBookRepository.searchBookBy(request.getBookIsbn(),
                request.getBookName(),
                request.getAuthorName(),
                request.getCategories());

        return Response.builder().books(ObjectConverterUtil.convertAll(books, BookDto.class)).build();
      }
}
