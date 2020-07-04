package com.this_rc.bookmicroservice.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
@ToString
@Builder
@Getter
public class BookResponse {
    List<ExternalBookDto> books;
    ExternalBookDto book;
    String message;


}
