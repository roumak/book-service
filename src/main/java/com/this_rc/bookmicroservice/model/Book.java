package com.this_rc.bookmicroservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "book")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "book_isbn")
    private String bookIsbn;

    @Column(name = "book_title")
    private String bookName;

    @Column(name = "author_name")
    private String authorName;

    @Column(name="book_mrp")
    private BigDecimal maxRetailPrice;

    @Column(name="book_cp")
    private BigDecimal costPrice;

    @Column(name="book_discount")
    private BigDecimal discount;

    @Column(name = "book_category")
    private String category;

    @Column(name = "book_description")
    private String description;

    @Column(name = "book_count")
    private int bookCount;

    @Column(name = "added_date")
    @CreationTimestamp
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Date procurementDate;

    private Book(@Nullable String bookIsbn,
                 @Nullable String bookName,
                 @Nullable String authorName,
                 @Nullable String category){
        this.bookIsbn=bookIsbn;
        this.bookName= bookName;
        this.authorName = authorName;
        this.category = category;
    }

    public static Book from(String bookIsbn,String bookName, String authorName, String category){
        return new Book(bookIsbn, bookName, authorName, category);
    }
}
