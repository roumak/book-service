package com.epam.jpop.bookmicroservice.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @Size(min = 2, max = 40, message = "book name should be between 2 and 40")
    private String bookName;

    @NotNull
    @NotEmpty
    private int count;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 40, message = "author name should be between 2 and 40")
    private String authorName;

    @Size(min = 1, max = 30, message = "category must be between 1 and 30")
    private String category;

    @Size(max = 150, message = "description must be less than 150 characters")
    private String description;

    @Override
    public String toString() {
        return bookName + " " + authorName + " " + category;
    }

}
