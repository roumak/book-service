package com.this_rc.bookmicroservice.services;

import com.this_rc.bookmicroservice.dto.BookResponse;
import com.this_rc.bookmicroservice.dto.BookSearchDto;
import com.this_rc.bookmicroservice.exceptions.NoObjectFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest  {


    @Autowired
    BookService bookService;

    @Test
    public void  searchBooksByParametersTest(){
        BookSearchDto bookSearchDto = BookSearchDto.builder()
                .authorName("Aamish Tripathi")
                .build();

        BookResponse response=bookService.searchBooksByParameters(bookSearchDto);

//        assertEquals(response.getBooks().);
        System.out.println(bookSearchDto);
        System.out.println(response);
    }
    @Test
    public void getBookById() throws NoObjectFoundException {
        BookResponse book = bookService.getBookById(1L);
        System.out.println(book);
    }

}