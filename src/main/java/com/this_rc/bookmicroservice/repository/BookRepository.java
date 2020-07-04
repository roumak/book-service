package com.this_rc.bookmicroservice.repository;

import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate;
import com.this_rc.bookmicroservice.dto.BookSearchDto;
import com.this_rc.bookmicroservice.model.Book;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("Select b from Book b where b.bookIsbn=:bookDto")
    public Iterable<Book> searchBookBasedOnAttribute(@Param("bookDto") BookSearchDto bookSearchDto);

}
