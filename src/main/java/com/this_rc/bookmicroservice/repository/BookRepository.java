package com.this_rc.bookmicroservice.repository;

import com.this_rc.bookmicroservice.dto.BookSearchDto;
import com.this_rc.bookmicroservice.model.Book;
import com.this_rc.bookmicroservice.repository.BookRepositoryAPI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface BookRepository extends BookRepositoryAPI, JpaRepository<Book, Long> {

    @Override
    default List<Book> getAllBooks(){
        return findAll();
    }

    @Override
    default List<Book> searchBookByParams(BookSearchDto book){
        return searchBookBy(book.getBookIsbn(), book.getBookTitle(), book.getAuthorName(), book.getCategory() );
    }

    @Query("Select b from Book b WHERE " +
            " b.bookIsbn LIKE :#{ #isbn == null || #isbn.isEmpty() ? '%' : '%'+#isbn+'%' } " +
            " AND b.bookTitle LIKE :#{ #bookTitle == null || #bookTitle.isEmpty() ? '%' : '%'+#bookTitle+'%' } " +
            " AND b.authorName LIKE :#{ #authorName== null || #authorName.isEmpty() ? '%' : '%'+#authorName+'%' } "+
            " AND b.category LIKE :#{ #category==null || #category.isEmpty() ? '%' : '%'+#category+'%' } "
            )
    List<Book> searchBookBy(String isbn, String bookTitle, String authorName, String category);

    @Override default Book saveBook(Book book){
       return saveAndFlush(book);
    }

    @Override default void deleteBookById(long id){
         deleteById(id);
    }
}
