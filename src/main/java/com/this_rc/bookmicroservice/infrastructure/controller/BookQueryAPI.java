package com.this_rc.bookmicroservice.infrastructure.controller;

import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@NoArgsConstructor
@Setter
public class BookQueryAPI {

    public String bookIsbn;

    public String bookTitle;

    public String authorName;

    public String type;

    public BigDecimal maxRetailPrice;

    public BigDecimal costPrice;

    public BigDecimal discount;

    public String category;

    public String description;

    public int bookCount;

    public Date procurementDate;

}
