package com.this_rc.bookmicroservice.controller;

import com.this_rc.bookmicroservice.dto.BookSearchDto;
import com.this_rc.bookmicroservice.dto.BookDto;
import com.this_rc.bookmicroservice.dto.InternalBookDto;
import com.this_rc.bookmicroservice.exceptions.NoObjectFoundException;
import com.this_rc.bookmicroservice.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
//@CrossOrigin(origins = "http://localhost:3000")
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

    @PostMapping("/search")
    public ResponseEntity<Object> searchBooksByParameters(@RequestBody BookSearchDto request){
        return  new ResponseEntity<>(theBookService.searchBooksByParameters(request), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Object> addBook(@RequestBody InternalBookDto newBook) {
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
