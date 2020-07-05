package com.this_rc.bookmicroservice.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class InternalBookDto {
    @NotNull
    @NotEmpty
    private Long bookId;

    @NotNull
    @NotEmpty
    private Long bookIsbn;

    @NotNull
    @NotEmpty
    @Size(max = 40, message = "book name should be between 2 and 40")
    private String bookTitle;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 40, message = "author name should be between 2 and 40")
    private String authorName;

    @NotNull
    @NotEmpty(message = "MRP must not be null or empty")
    private BigDecimal maxRetailPrice;

    @NotNull
    @NotEmpty(message = "discount must not be null or empty")
    private BigDecimal discount;

    @NotNull
    @NotEmpty(message = "cost price must not be null or empty")
    private BigDecimal costPrice;

    @Size(min = 1, max = 30, message = "category must be between 1 and 30 characters")
    private String category;

    @Size(max = 150, message = "description must be less than 150 characters")
    private String description;

    @NotNull
    @NotEmpty
    private int count;
}
