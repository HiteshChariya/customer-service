package com.bookstore.customer.service;

import com.bookstore.customer.feign.BookClientService;
import com.bookstore.customer.response.BookClientResponse;
import com.bookstore.customer.response.BookDetailResponse;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class FiengClientService {

    @Autowired
    private BookClientService bookClientService;

    public BookDetailResponse findBookById(Long bookId) {
        String response = bookClientService.findBookById(bookId);
        log.info("response -> {}", response);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try{
            BookClientResponse bookClientResponse = mapper.readValue(response, BookClientResponse.class);
            return bookClientResponse.getResponse().get(0);
        }catch (Exception e){
            log.error("ERROR WHILE CONVERT VALUES - > {}", e.getMessage(), e);
        }
        return null;
    }
}
