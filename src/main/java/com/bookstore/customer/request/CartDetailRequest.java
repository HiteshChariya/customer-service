package com.bookstore.customer.request;

import lombok.Data;

@Data
public class CartDetailRequest {

    private Long cartId;
    private Long bookId;
    private Long cartItem;

}
