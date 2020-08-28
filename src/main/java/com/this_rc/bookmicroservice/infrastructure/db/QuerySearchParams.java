package com.this_rc.bookmicroservice.infrastructure.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Builder
@Getter
@AllArgsConstructor
public class QuerySearchParams {

    private final String bookIsbn;

    private final String bookTitle;

    private final String authorName;

    private final String category;
}
