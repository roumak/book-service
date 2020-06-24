package com.epam.jpop.bookmicroservice.controller;

import com.epam.jpop.bookmicroservice.dto.BookDto;
import com.epam.jpop.bookmicroservice.exceptions.NoObjectFoundException;
import com.epam.jpop.bookmicroservice.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "http://localhost:3000")
public class BookRestController {

    @Autowired
    private BookService theBookService;

    @GetMapping()
    public ResponseEntity<Object> getAllBooks() {
        return new ResponseEntity<>(theBookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/{book_id}")
    public ResponseEntity<Object> getBookById(@PathVariable("book_id") final Long bookId) throws NoObjectFoundException {
        return new ResponseEntity<>(theBookService.getBookById(bookId), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Object> addBook(@RequestBody BookDto newBook) {
        return new ResponseEntity<>(theBookService.saveBook(newBook), HttpStatus.OK);
    }

    @DeleteMapping("/{book_id}")
    public ResponseEntity<Object> deleteBookById(@PathVariable("book_id") Long bookId) {
        return new ResponseEntity<>(theBookService.deleteBookById(bookId), HttpStatus.OK);
    }

    @PutMapping("/{book_id}")
    public ResponseEntity<Object> updateBookById(@PathVariable("book_id") Long bookId, @RequestBody BookDto newBook) {
        newBook.setBookId(bookId);
        return new ResponseEntity<>(theBookService.updateBook(newBook), HttpStatus.OK);
    }

}
