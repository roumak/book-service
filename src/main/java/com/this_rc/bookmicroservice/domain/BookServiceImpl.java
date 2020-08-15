package com.this_rc.bookmicroservice.domain;

import com.this_rc.bookmicroservice.infrastructure.exceptions.NoObjectFoundException;
import com.this_rc.bookmicroservice.domain.util.ObjectConverterUtil;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;


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
    public List<Book> getAllBooks() {
        List<Book> allBooks = theBookRepository.getAllBooks();
        log.info(String.format("Retrieving all books %s", allBooks));
        return allBooks;
    }

    @Override
    public Book getBookById(Long bookId) throws NoObjectFoundException {
        return theBookRepository
                .findById(bookId)
                .orElseThrow(
                        () -> new NoObjectFoundException("No object found with this book id"));
    }

    @Override
    public Book getBookByIsbn(String isbn) throws NoObjectFoundException{
        return theBookRepository
                .findByBookIsbn(isbn)
                .orElseThrow(
                        ()-> new NoObjectFoundException("No object found with this isbn"));

    }

    @Override
    public Book saveBook(Book newBook) {
        Book outputBook;
        try {
            outputBook = theBookRepository.saveBook(newBook);
        } catch (ConstraintViolationException e) {
            throw new ValidationException("saved data does not meet required constraints");
        }
        return outputBook;
    }

    @Override
    public void deleteBookById(long bookId) {
        theBookRepository.deleteBookById(bookId);
    }

    @Override
    public Book updateBook(Book book) {
        log.info("updating book, bookId" + book.getBookId());
       return theBookRepository.saveBook(book);
    }


    @Override
    public List<Book> searchBooksByParameters(BookSearchParams request) {

        if(request.isEmpty()){

        }
       return theBookRepository.searchBookByParams(request);

    }

}
