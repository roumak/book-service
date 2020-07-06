package com.this_rc.bookmicroservice.repository;

import com.this_rc.bookmicroservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("Select b from Book b where "
            + "b.bookIsbn=:isbn OR "
            + "b.bookTitle=:bookName OR "
            + "b.authorName=:authorName OR "
            + "b.category=:category")
    List<Book> searchBookBy(String isbn, String bookName, String authorName, String category);

    Book findByBookIsbn(Long bookIsbn);
}
