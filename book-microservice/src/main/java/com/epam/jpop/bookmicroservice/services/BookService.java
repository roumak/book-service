package com.epam.jpop.bookmicroservice.services;

import com.epam.jpop.bookmicroservice.exceptions.NoObjectFoundException;
import com.epam.jpop.bookmicroservice.model.Book;
import com.epam.jpop.bookmicroservice.repository.BookRepositiry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class BookService {
    @Autowired
    BookRepositiry bookRepo;

    Logger log= LoggerFactory.getLogger(BookService.class);

    public List<Book> getAllBooks(){
        List<Book> allBooks =  bookRepo.findAll();
        log.info("Retrieving all books",allBooks);
       return Collections.unmodifiableList(allBooks);
    }

    public Book getBookById(Long bookId)throws  NoObjectFoundException{
       Optional<Book> retrievedBookOptional= bookRepo.findById(bookId);
       retrievedBookOptional.orElseThrow( () -> new NoObjectFoundException("No book found with this book id"));
       return retrievedBookOptional.get();
    }
    public Book saveBook(Book newBook){
            newBook.setBookId(null);
           Book addedBook= bookRepo.saveAndFlush(newBook);
           log.info("added new book to db",addedBook);
           return addedBook;
    }

    public boolean deleteBookById(long bookId){
       try{
           bookRepo.deleteById(bookId);
           return true;
       }catch (Exception e){
           log.error(e.getMessage());
           return false;
       }
    }

    public Book updateBook(Book book){
        log.info("updating book, bookId"+ book.getBookId());
        Book addedBook= bookRepo.saveAndFlush(book);
        return addedBook;
    }


}
