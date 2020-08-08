package com.this_rc.bookmicroservice.infrastructure.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
@ToString
@Builder
@Getter
class Response {
    private List<ResponseBookDto> books;
    private ResponseBookDto book;
    private HttpStatus httpStatus;
    private String message;
}
