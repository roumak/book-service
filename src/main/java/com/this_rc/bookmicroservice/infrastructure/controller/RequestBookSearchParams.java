package com.this_rc.bookmicroservice.infrastructure.controller;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.lang.Nullable;

@ApiModel(description = "Book search request")
@AllArgsConstructor
class RequestBookSearchParams {
    @Nullable
    public final String bookIsbn;

    @Nullable
    public final String bookTitle;

    @Nullable
    public final String authorName;

    @Nullable
    public final String category;

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
