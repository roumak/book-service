package com.this_rc.bookmicroservice.infrastructure.db;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class QuerySearchParams {

    private String bookIsbn;

    private String bookTitle;

    private String authorName;

    private String category;
}
