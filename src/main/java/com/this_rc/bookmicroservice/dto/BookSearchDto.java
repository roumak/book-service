package com.this_rc.bookmicroservice.dto;


import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

@ApiModel(description = "Book search request")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class BookSearchDto {

    @Nullable
    private String bookIsbn;

    @Nullable
    private String bookTitle;

    @Nullable
    private String authorName;

    @Nullable
    private String category;

    @Override
    public String toString() {
        return "BookSearchRequest {" +
                "bookIsbn='" + bookIsbn + '\'' +
                ", bookName='" + bookTitle + '\'' +
                ", authorName='" + authorName + '\'' +
                ", categories='" + category + '\'' +
                '}';
    }

    public boolean isEmpty() {
        return  bookIsbn == null
                && bookTitle == null
                && authorName == null
                && category == null;
    }
}
