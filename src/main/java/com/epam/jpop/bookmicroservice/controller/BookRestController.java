package com.epam.jpop.bookmicroservice.controller;

import com.epam.jpop.bookmicroservice.dto.BookDto;
import com.epam.jpop.bookmicroservice.exceptions.NoObjectFoundException;
import com.epam.jpop.bookmicroservice.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class BookRestController {

    @Autowired
    private BookService theBookService;

    @GetMapping("/books")
    public ResponseEntity<Object> getAllBooks(){
        return  new ResponseEntity<>(theBookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/books/{book_id}")
    public ResponseEntity<Object> getBookById(@PathVariable("book_id") final Long bookId)throws NoObjectFoundException{
        return new ResponseEntity<>(theBookService.getBookById(bookId),HttpStatus.OK);
    }

    @PostMapping("/books")
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto newBook){
        return new ResponseEntity<>(theBookService.saveBook(newBook),HttpStatus.OK);
    }

    @DeleteMapping("/books/{book_id}")
    public ResponseEntity<Object> deleteBookById(@PathVariable("book_id") Long bookId){
        ResponseEntity<Object> resp = new ResponseEntity<>("deleted",HttpStatus.OK);
        theBookService.deleteBookById(bookId);
        return resp;
    }

    @PutMapping("/books/{book_id}")
    public ResponseEntity<Object> updateBookById(@PathVariable("book_id") Long bookId,@RequestBody BookDto newBook){
        newBook.setBookId(bookId);
        return new ResponseEntity<>(theBookService.updateBook(newBook),HttpStatus.OK);
    }


}
