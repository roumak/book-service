package com.this_rc.bookmicroservice.infrastructure.controller;

import com.this_rc.bookmicroservice.domain.Facade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
class BookRestController {

    Logger log = LoggerFactory.getLogger(BookRestController.class);

    @Autowired
    private Facade domainFacade;

    @GetMapping()
    public ResponseEntity<Object> getAllBooks() {
        return ResponseEntity.ok(domainFacade.getAllBooks());
    }

    @GetMapping("/{book_id}")
    public ResponseEntity<Object> getBookById(@PathVariable("book_id") final Long bookId) {
        return ResponseEntity.ok(domainFacade.getBookById(bookId));
    }

    @PostMapping("/search")
    public ResponseEntity<Object> searchBooksByParameters(@RequestBody BookSearchParamsQuery request){
        log.info("search request: "+request);
        return  ResponseEntity.ok(domainFacade.searchBookByParams(request));
    }

    @PostMapping()
    public ResponseEntity<Object> addBook(@RequestBody CreateUpdateBookDto newBook) {
        return ResponseEntity.ok(domainFacade.saveBook(newBook));
    }

    @DeleteMapping("/{book_id}")
    public ResponseEntity<Object> deleteBookById(@PathVariable("book_id") Long bookId) {
        return ResponseEntity.ok(domainFacade.deleteBookById(bookId));
    }

    @PutMapping("/{book_id}")
    public ResponseEntity<Object> updateBookById(@PathVariable("book_id") Long bookId, @RequestBody CreateUpdateBookDto newBook) {
        newBook.bookId=bookId;
        return ResponseEntity.ok(domainFacade.updateBook(newBook));
    }

}
