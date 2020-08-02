package com.this_rc.bookmicroservice.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@ApiModel(description = "Book details")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class BookDto {
    @NotNull
    @NotEmpty
    private Long bookId;

    @NotNull
    @NotEmpty
    private String bookIsbn;

    @NotNull
    @NotEmpty
    @Size(max = 40, message = "book name should be between 2 and 40")
    private String bookTitle;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 40, message = "author name should be between 2 and 40")
    private String authorName;

    @NotNull
    @NotEmpty
    @Size(min = 5, max = 20, message = "type should be between 5 and 20")
    private String type;

    @NotNull
    @NotEmpty(message = "MRP must not be null or empty")
    private BigDecimal maxRetailPrice;

    @NotNull
    @NotEmpty(message = "MRP must not be null or empty")
    private BigDecimal discount;

    @Size(min = 1, max = 30, message = "category must be between 1 and 30 characters")
    private String category;

    @Size(max = 150, message = "description must be less than 150 characters")
    private String description;

    @NotNull
    @NotEmpty
    private int count;

    @Override
    public String toString() {
        return bookTitle + " " + authorName + " " + category;
    }

}
