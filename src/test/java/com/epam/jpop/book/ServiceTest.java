package com.epam.jpop.book;

import com.epam.jpop.bookmicroservice.services.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ServiceTest {
    @Autowired
    BookService bookService;

    @Test
    public void testAllBookRetrieval(){

    }

}
