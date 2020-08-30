package com.this_rc.bookmicroservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Setter
@Getter
@ToString
@Component
class BookSearchParams {

    private String bookIsbn;

    private String bookTitle;

    private String authorName;

    private String category;
}
