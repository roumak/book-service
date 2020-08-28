package com.this_rc.bookmicroservice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
public class BookCommandDto {
    private String bookId;

    private String bookIsbn;

    private String bookTitle;

    private String authorName;

    private String type;

    private BigDecimal maxRetailPrice;

    private BigDecimal costPrice;

    private BigDecimal discount;

    private String category;

    private String description;

    private int bookCount;

}
