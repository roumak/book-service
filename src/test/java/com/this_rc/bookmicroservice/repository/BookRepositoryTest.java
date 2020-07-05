package com.this_rc.bookmicroservice.repository;

import com.this_rc.bookmicroservice.model.Book;
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
    BookRepository bookRepository;

    @Test
    public void searchBookBasedOnAttribute() {
     List<Book> books = bookRepository.searchBookBy(null,"Scion of Iksavaku", "Aamish Tripathi",null);
        System.out.println(books);
    }
}