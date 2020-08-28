package com.this_rc.bookmicroservice.infrastructure.db;

import com.this_rc.bookmicroservice.domain.BookCommandDto;
import com.this_rc.bookmicroservice.domain.BookQueryDto;
import com.this_rc.bookmicroservice.infrastructure.controller.BookQueryAPI;
import com.this_rc.bookmicroservice.util.ObjectConverterUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("Select NEW com.this_rc.bookmicroservice.domain.BookQueryDto(" +
            "b.bookIsbn, b.bookTitle, b.authorName, b.type, b.maxRetailPrice," +
            "b.discount, b.category, b.description, b.bookCount) from Book b")
    List<BookQueryDto> getAllBooks();

    default List<BookQueryDto> searchBookByParams(QuerySearchParams book){
        return searchBookBy(book.getBookIsbn(), book.getBookTitle(), book.getAuthorName(), book.getCategory() );
    }

    @Query("Select new com.this_rc.bookmicroservice.domain.BookQueryDto(b.bookIsbn, b.bookTitle, b.authorName, b.type, b.maxRetailPrice, b.discount, b.category, b.description, b.bookCount)" +
            " from Book b WHERE " +
            " b.bookIsbn LIKE :#{ #isbn == null || #isbn.isEmpty() ? '%' : '%'+#isbn+'%' } " +
            " AND b.bookTitle LIKE :#{ #bookTitle == null || #bookTitle.isEmpty() ? '%' : '%'+#bookTitle+'%' } " +
            " AND b.authorName LIKE :#{ #authorName== null || #authorName.isEmpty() ? '%' : '%'+#authorName+'%' } "+
            " AND b.category LIKE :#{ #category==null || #category.isEmpty() ? '%' : '%'+#category+'%' } "
            )
    List<BookQueryDto> searchBookBy(String isbn, String bookTitle, String authorName, String category);

    default BookQueryDto saveBook(BookCommandDto newBook){
       return Optional.of(newBook)
               .map(book->ObjectConverterUtil.convert(book,Book.class))
               .map(this::saveAndFlush)
               .map(book->ObjectConverterUtil.convert(book,BookQueryDto.class))
               .orElseThrow(()-> new PersistenceException("could not save Book"));

    }

    default Optional<BookQueryDto> getBookById(long bookId){
       return findById(bookId)
               .map(book -> ObjectConverterUtil.convert(book, BookQueryDto.class));
    }

    @Query("Select NEW com.this_rc.bookmicroservice.domain.BookQueryDto(" +
            "b.bookIsbn, b.bookTitle, b.authorName, b.type, b.maxRetailPrice," +
            "b.discount, b.category, b.description, b.bookCount) " +
            "from Book b where b.bookIsbn = bookIsbn  ")
    Optional<BookQueryDto> findByBookIsbn(String bookIsbn);

    default void deleteBookById(long id){
         deleteById(id);
    }
}
