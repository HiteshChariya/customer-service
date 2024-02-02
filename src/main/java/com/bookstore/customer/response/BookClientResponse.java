package com.bookstore.customer.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookClientResponse {

    private Boolean validation;
    private String error;
    private String message;
    private List<BookDetailResponse> response;

}

