package com.this_rc.bookmicroservice.domain;

import com.this_rc.bookmicroservice.infrastructure.controller.BookQueryAPI;
import com.this_rc.bookmicroservice.infrastructure.controller.BookSearchParamsQuery;
import com.this_rc.bookmicroservice.infrastructure.controller.CreateUpdateBookDto;
import com.this_rc.bookmicroservice.infrastructure.controller.NoSearchResultsException;
import com.this_rc.bookmicroservice.infrastructure.exceptions.NoRecordsFoundException;
import com.this_rc.bookmicroservice.util.ObjectConverterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

import static com.this_rc.bookmicroservice.util.ObjectConverterUtil.convert;

@Component
public class Facade {

    Logger log = LoggerFactory.getLogger(Facade.class);
    private final BookService bookService;

    @Autowired
    public Facade(BookService bookService){
        this.bookService=bookService;
    }

    public List<BookQueryAPI> getAllBooks() {
       return Optional
               .of(bookService.getAllBooks())
               .map(dtos -> ObjectConverterUtil.convert(dtos,BookQueryAPI.class))
               .orElseThrow(()-> new NoRecordsFoundException("No data present in database"));
    }

    public BookQueryAPI getBookById(Long bookId) {
        return Optional
                .of(bookService.getBookById(bookId))
                .map(dto-> convert(dto, BookQueryAPI.class))
                .orElseThrow(()-> new NoRecordsFoundException("No books found with the id"));
    }

    public BookQueryAPI getBookByIsbn(String isbn) {
        return Optional
                .of(bookService.getBookByIsbn(isbn))
                .map(dto -> convert(dto, BookQueryAPI.class))
                .orElseThrow(()->new NoRecordsFoundException("No books found with the given isbn"));
    }

    public List<BookQueryAPI> searchBookByParams(BookSearchParamsQuery searchParams){
        return Optional
                .of(searchParams)
                .map(dto ->{ System.out.println("Before: "+dto); return dto;})
                .map(dto -> ObjectConverterUtil.convert(dto,BookSearchParams.class ))
                .map(dto ->{ System.out.println("After: "+dto); return dto;})
                .map(bookService::searchBooksByParameters)
                .map(dtos-> ObjectConverterUtil.convert(dtos,BookQueryAPI.class))
                .orElseThrow(()-> new NoSearchResultsException("No search results found"));
    }

    public BookQueryAPI saveBook(CreateUpdateBookDto newBookDto) {
        return Optional
                .of(newBookDto)
                .map(dto -> convert(dto,BookCommandDto.class ))
                .map(bookService::saveBook)
                .map(dtos-> convert(dtos,BookQueryAPI.class))
                .orElseThrow(()-> new PersistenceException("Could not save book"));
    }

    public String deleteBookById(long id){
        bookService.deleteBookById(id);
        return "deleted";
    }

    public BookQueryAPI updateBook(CreateUpdateBookDto updatedBook){
        return Optional.of(updatedBook)
                .map(book-> convert(book,BookCommandDto.class))
                .map( bookService::updateBook)
                .map(book->convert(book,BookQueryAPI.class))
                .orElseThrow(()-> new PersistenceException("Could not update book"));
    }

}
