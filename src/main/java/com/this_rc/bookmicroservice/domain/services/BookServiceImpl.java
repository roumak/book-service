package com.this_rc.bookmicroservice.domain.services;

import com.this_rc.bookmicroservice.domain.Book;
import com.this_rc.bookmicroservice.domain.BookSearchDto;
import com.this_rc.bookmicroservice.domain.BookDto;
import com.this_rc.bookmicroservice.domain.Response;
import com.this_rc.bookmicroservice.domain.InternalBookDto;
import com.this_rc.bookmicroservice.infrastructure.exceptions.NoObjectFoundException;
import com.this_rc.bookmicroservice.infrastructure.db.BookRepositoryAPI;
import com.this_rc.bookmicroservice.domain.util.ObjectConverterUtil;
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
class BookServiceImpl implements BookService {

    private BookRepositoryAPI theBookRepository;

    @Autowired
    BookServiceImpl setBookRepository(BookRepositoryAPI theBookRepository){
         this.theBookRepository=theBookRepository;
         return this;
    }

    private final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    @Override
    public Response getAllBooks() {
        List<Book> allBooks = theBookRepository.getAllBooks();
        log.info(String.format("Retrieving all books %s", allBooks));
        List<BookDto> bookDtoList = ObjectConverterUtil.convertAll(Collections.unmodifiableList(allBooks),
                BookDto.class);
        return Response.builder().books(bookDtoList).build();
    }

    @Override
    public Response getBookById(Long bookId) throws NoObjectFoundException {
        Optional<Book> retrievedBookOptional = theBookRepository.findById(bookId);
        BookDto bookDto = ObjectConverterUtil
                .convert(retrievedBookOptional
                        .orElseThrow(
                                () -> new NoObjectFoundException("No object found with this ID")), BookDto.class);
        return Response.builder().book(bookDto).build();
    }

    @Override
    public Response getBookByIsbn(String isbn){
        Book book=theBookRepository.findByBookIsbn(isbn).get();

        return Response.builder()
                .book(ObjectConverterUtil.convert(book,BookDto.class))
                .build();
    }

    @Override
    public Response saveBook(InternalBookDto newBook) {
        Book book = ObjectConverterUtil.convert(newBook, Book.class);
        log.info("\n" + book.toString() + "\n");
        Book outputBook = null;
        try {
            outputBook = theBookRepository.saveBook(book);
        } catch (ConstraintViolationException e) {
            throw new ValidationException("saved data does not meet required constraints");
        }
        return Response.builder().book(ObjectConverterUtil.convert(outputBook, BookDto.class)).build();
    }

    @Override
    public Response deleteBookById(long bookId) {
        theBookRepository.deleteBookById(bookId);
        return Response.builder().message("book deleted").build();
    }

    @Override
    public Response updateBook(BookDto book) {
        log.info("updating book, bookId" + book.getBookId());
        Book addedBook = theBookRepository.saveBook(ObjectConverterUtil.convert(book, Book.class));
        return Response.builder().book(ObjectConverterUtil.convert(addedBook, BookDto.class)).build();
    }


    @Override
    public Response searchBooksByParameters(BookSearchDto request) {

        if(request.isEmpty()){
           return Response.builder().message("Empty request encountered, Check request field mappings").build();
        }
        List<Book> books= theBookRepository.searchBookByParams(request);

        return Response.builder().books(ObjectConverterUtil.convertAll(books, BookDto.class)).build();
      }
}
