package com.this_rc.bookmicroservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.Nullable;

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
    private String bookTitle;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "book_type")
    private String type;

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
        this.bookTitle= bookName;
        this.authorName = authorName;
        this.category = category;
    }

    public static Book from(String bookIsbn,String bookName, String authorName, String category){
        return new Book(bookIsbn, bookName, authorName, category);
    }
}
