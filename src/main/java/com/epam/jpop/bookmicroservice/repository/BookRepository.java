package com.epam.jpop.bookmicroservice.repository;

import com.epam.jpop.bookmicroservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
