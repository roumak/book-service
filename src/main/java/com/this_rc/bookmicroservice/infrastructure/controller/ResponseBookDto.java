package com.this_rc.bookmicroservice.infrastructure.controller;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@ApiModel(description = "Book details")
@AllArgsConstructor
class ResponseBookDto {
    @NotNull
    @NotEmpty
    public Long bookId;

    @NotNull
    @NotEmpty
    public String bookIsbn;

    @NotNull
    @NotEmpty
    @Size(max = 40, message = "book name should be between 2 and 40")
    public String bookTitle;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 40, message = "author name should be between 2 and 40")
    public String authorName;

    @NotNull
    @NotEmpty
    @Size(min = 5, max = 20, message = "type should be between 5 and 20")
    public String type;

    @NotNull
    @NotEmpty(message = "MRP must not be null or empty")
    public BigDecimal maxRetailPrice;

    @NotNull
    @NotEmpty(message = "MRP must not be null or empty")
    public BigDecimal discount;

    @Size(min = 1, max = 30, message = "category must be between 1 and 30 characters")
    public String category;

    @Size(max = 150, message = "description must be less than 150 characters")
    public String description;

    @NotNull
    @NotEmpty
    public int count;

    @Override
    public String toString() {
        return bookTitle + " " + authorName + " " + category;
    }

}
