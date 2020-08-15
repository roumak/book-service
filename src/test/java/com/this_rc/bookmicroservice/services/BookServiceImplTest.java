package com.this_rc.bookmicroservice.services;

import com.this_rc.bookmicroservice.infrastructure.controller.response.InternalBookDto;
import com.this_rc.bookmicroservice.infrastructure.controller.response.Response;
import com.this_rc.bookmicroservice.infrastructure.controller.request.RequestBookSearchParams;
import com.this_rc.bookmicroservice.domain.BookService;
import com.this_rc.bookmicroservice.infrastructure.exceptions.NoObjectFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceImplTest {

    @Autowired
    BookService bookService;

    @Test
    public void getAllBooks(){
        Response book = bookService.getAllBooks();
        Assert.assertEquals("Total number of books in data.sql changed",
                9,book.getBooks().size());
    }

    @Test
    public void getBookById() throws NoObjectFoundException {
        Response book = bookService.getBookById(1L);

        Assert.assertEquals("isbn number changed","1101010000000",book.getBook().getBookIsbn());
        Assert.assertEquals("Book name changed","Scion of Iksavaku",book.getBook().getBookTitle());
        Assert.assertEquals("Author name changed","Aamish Tripathi",book.getBook().getAuthorName());
        Assert.assertEquals("Category changed","Fiction",book.getBook().getCategory());
        Assert.assertEquals("book count changed",9,book.getBook().getCount());
    }

    @Test
    public void SavingBook(){
        String ISBN= "1023234334545";
        InternalBookDto book= InternalBookDto.builder().bookIsbn(ISBN)
                .bookTitle("Wings of fire")
                .authorName("Arun Tiwari")
                .category("Autobiography")
                .description("It is an autobography of Dr. A.P.J Abdul Kalam")
                .costPrice(BigDecimal.valueOf(299.34))
                .maxRetailPrice(BigDecimal.valueOf(399.00))
                .maxRetailPrice(BigDecimal.valueOf(200.00))
                .count(75)
                .build();

        bookService.saveBook(book);
        Response response=bookService.getBookByIsbn(ISBN);
        Assert.assertEquals("ISBN did not match",ISBN,response.getBook().getBookIsbn());
        Assert.assertEquals("Book title did not match","Wings of fire",response.getBook().getBookTitle());
        Assert.assertEquals("Category did not match","Autobiography",response.getBook().getCategory());
    }

    @Test
    public void  searchBooksByParameters(){
        RequestBookSearchParams requestBookSearchParams = RequestBookSearchParams.builder()
                .authorName("Aamish Tripathi")
                .build();

        Response response=bookService.searchBooksByParameters(requestBookSearchParams);
        System.out.println(response);
    }

}