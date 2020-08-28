package com.this_rc.bookmicroservice.domain;

import lombok.AllArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
class BookSearchParams {

    @Nullable
    private final String bookIsbn;

    @Nullable
    private final String bookTitle;

    @Nullable
    private final String authorName;

    @Nullable
    private final String category;

    public String getBookIsbn() {
        return bookIsbn;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getCategory() {
        return category;
    }
}
