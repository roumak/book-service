package com.this_rc.bookmicroservice.infrastructure.controller;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

@ApiModel(description = "Book search request")
@NoArgsConstructor
@Setter
@ToString
public class BookSearchParamsQuery {

    public  String bookIsbn;

    public  String bookTitle;

    public  String authorName;

    public String category;


    public boolean isEmpty() {
        return  bookIsbn == null
                && bookTitle == null
                && authorName == null
                && category == null;
    }
}
