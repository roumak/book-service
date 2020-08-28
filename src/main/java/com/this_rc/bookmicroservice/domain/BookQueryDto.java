package com.this_rc.bookmicroservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class BookQueryDto {

    private String bookIsbn;

    private String bookTitle;

    private String authorName;

    private String type;

    private BigDecimal maxRetailPrice;

    private BigDecimal discount;

    private String category;

    private String description;

    private int bookCount;

    BookQueryDto(String bookIsbn, String bookTitle, String authorName, String type, BigDecimal maxRetailPrice, BigDecimal discount, String category, String description, int bookCount) {
        this.bookIsbn = bookIsbn;
        this.bookTitle = bookTitle;
        this.authorName = authorName;
        this.type = type;
        this.maxRetailPrice = maxRetailPrice;
        this.discount = discount;
        this.category = category;
        this.description = description;
        this.bookCount = bookCount;
    }
}
