package com.bookstore.customer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "book-service", url = "http://localhost:8081/book-service/api", path = "/book")
public interface BookClientService {

	@GetMapping(value = "/bookById", consumes = { MediaType.APPLICATION_JSON_VALUE })
    String findBookById(@RequestParam("id") Long bookId);
}
