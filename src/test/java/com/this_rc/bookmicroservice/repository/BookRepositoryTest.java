package com.this_rc.bookmicroservice.repository;

import com.this_rc.bookmicroservice.domain.Book;
import com.this_rc.bookmicroservice.infrastructure.controller.request.RequestBookSearchParams;
import com.this_rc.bookmicroservice.domain.BookRepositoryAPI;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BookRepositoryTest {

    @Autowired
    BookRepositoryAPI bookRepository;

    @Test
    public void searchBookBasedOnAttribute() {
     RequestBookSearchParams searchedBook = RequestBookSearchParams.builder().authorName("amish").build();
     List<Book> books = bookRepository.searchBookByParams(searchedBook);
        Assert.assertEquals("count of books with" +
                " Author: amish did not match",6,books.size());
    }
}