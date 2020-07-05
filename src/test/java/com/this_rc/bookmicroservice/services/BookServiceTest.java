package com.this_rc.bookmicroservice.services;

import com.this_rc.bookmicroservice.dto.Response;
import com.this_rc.bookmicroservice.dto.BookSearchDto;
import com.this_rc.bookmicroservice.exceptions.NoObjectFoundException;
import com.this_rc.bookmicroservice.model.Book;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.function.Consumer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest  {

    @Autowired
    BookService bookService;

    @Test
    public void getAllBooks(){
        Response book = bookService.getAllBooks();
        Assert.assertEquals("Total number of books in data.sql changed",
                8,book.getBooks().size());
    }

    @Test
    public void getBookById() throws NoObjectFoundException {
        Response book = bookService.getBookById(1L);

        Assert.assertEquals("isbn number changed",Long.valueOf("1101010000000"),book.getBook().getBookIsbn());
        Assert.assertEquals("Book name changed","Scion of Iksavaku",book.getBook().getBookName());
        Assert.assertEquals("Author name changed","Aamish Tripathi",book.getBook().getAuthorName());
        Assert.assertEquals("Category changed","Fiction",book.getBook().getCategory());
        Assert.assertEquals("book count changed",9,book.getBook().getCount());
    }

    @Test
    public void SavingBook(){

    }

    @Test
    public void  searchBooksByParameters(){
        BookSearchDto bookSearchDto = BookSearchDto.builder()
                .authorName("Aamish Tripathi")
                .build();

        Response response=bookService.searchBooksByParameters(bookSearchDto);
        System.out.println(response);
    }

}