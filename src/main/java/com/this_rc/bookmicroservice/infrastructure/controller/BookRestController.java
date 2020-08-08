package com.this_rc.bookmicroservice.infrastructure.controller;

import com.this_rc.bookmicroservice.infrastructure.exceptions.NoObjectFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
class BookRestController {

    @Autowired
    private ControllerPort controllerPort;

    @GetMapping()
    public ResponseEntity<Object> getAllBooks() {
        return new ResponseEntity<>(controllerPort.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/{book_id}")
    public ResponseEntity<Object> getBookById(@PathVariable("book_id") final Long bookId) throws NoObjectFoundException {
        return new ResponseEntity<>(controllerPort.getBookById(bookId), HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<Object> searchBooksByParameters(@RequestBody RequestBookSearchParams request){
        return  new ResponseEntity<>(controllerPort.searchBooksByParameters(request), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Object> addBook(@RequestBody RequestBookDto newBook) {
        return new ResponseEntity<>(controllerPort.saveBook(newBook), HttpStatus.OK);
    }

    @DeleteMapping("/{book_id}")
    public ResponseEntity<Object> deleteBookById(@PathVariable("book_id") Long bookId) {
        return new ResponseEntity<>(controllerPort.deleteBookById(bookId), HttpStatus.OK);
    }

    @PutMapping("/{book_id}")
    public ResponseEntity<Object> updateBookById(@PathVariable("book_id") Long bookId, @RequestBody RequestBookDto newBook) {
        newBook.bookId=bookId;
        return new ResponseEntity<>(controllerPort.updateBook(newBook), HttpStatus.OK);
    }

}
