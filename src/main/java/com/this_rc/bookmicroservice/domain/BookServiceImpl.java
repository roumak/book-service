package com.this_rc.bookmicroservice.domain;

import com.this_rc.bookmicroservice.util.ObjectConverterUtil;
import com.this_rc.bookmicroservice.infrastructure.db.BookRepository;
import com.this_rc.bookmicroservice.infrastructure.db.QuerySearchParams;
import com.this_rc.bookmicroservice.infrastructure.exceptions.NoRecordsFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;


@Service
class BookServiceImpl implements BookService {

    private BookRepository theBookRepository;

    @Autowired
    void setBookRepository(BookRepository theBookRepository){
         this.theBookRepository=theBookRepository;
    }

    private final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    @Override
    public List<BookQueryDto> getAllBooks() {
        List<BookQueryDto> allBooks = theBookRepository.getAllBooks();
        log.info(String.format("Retrieving all books %s", allBooks));
        return allBooks;
    }

    @Override
    public BookQueryDto getBookById(Long bookId) throws NoRecordsFoundException {
        return theBookRepository
                .getBookById(bookId)
                .orElseThrow(
                        () -> new NoRecordsFoundException("No object found with this book id"));
    }

    @Override
    public BookQueryDto getBookByIsbn(String isbn) throws NoRecordsFoundException {
        return theBookRepository
                .findByBookIsbn(isbn);
//                .orElseThrow(
//                        ()-> new NoRecordsFoundException("No object found with this isbn"));

    }

    @Override
    public BookQueryDto saveBook(BookCommandDto newBook) {
        BookQueryDto outputBook;
        try {
            outputBook = theBookRepository.saveBook(newBook);
        } catch (ConstraintViolationException e) {
            throw new ValidationException("Book data does not meet required constraints");
        }
        return outputBook;
    }

    @Override
    public void deleteBookById(long bookId) {
        theBookRepository.deleteBookById(bookId);
    }

    @Override
    public BookQueryDto updateBook(BookCommandDto book) {
        log.info("updating book, bookId" + book.getBookId());
       return theBookRepository.saveBook(book);
    }


    @Override
    public List<BookQueryDto> searchBooksByParameters(BookSearchParams searchParams) {
       log.info("search params"+searchParams);
        return Optional.of(searchParams)
                .map(params-> ObjectConverterUtil.convert(params, QuerySearchParams.class))
                .map(theBookRepository::searchBookByParams).get();
    }

}
