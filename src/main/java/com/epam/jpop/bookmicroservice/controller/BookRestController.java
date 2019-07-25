package com.epam.jpop.bookmicroservice.controller;

import com.epam.jpop.bookmicroservice.exceptions.NoObjectFoundException;
import com.epam.jpop.bookmicroservice.model.Book;
import com.epam.jpop.bookmicroservice.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookRestController {

    @Autowired
    BookService theBookService;

    @GetMapping("/books")
    public List<Book> getAllBooks(){
       // log.info("returned all books stored in stash");
        return theBookService.getAllBooks();
    }

    @GetMapping("/books/{book_id}")
    public Book getBookById(@PathVariable("book_id") final Long bookId)throws NoObjectFoundException{
        return theBookService.getBookById(bookId);
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book newBook){
        return theBookService.saveBook(newBook);
    }

    @DeleteMapping("/books/{book_id}")
    public String deleteBookById(@PathVariable("book_id") Long bookId){
        String resp;
        boolean isSuccessflag= theBookService.deleteBookById(bookId);
        if(isSuccessflag){
            resp= "Book id: <h4>"+bookId+"</h4> is deleted";
        }else{
            resp= "Exception occured, book couldnt be deleted";
        }
        return resp;
    }

    @PutMapping("/books/{book_id}")
    public Book updateBookById(@PathVariable("book_id") Long bookId,@RequestBody Book newBook){
        newBook.setBookId(bookId);
        return theBookService.updateBook(newBook);
    }


}
