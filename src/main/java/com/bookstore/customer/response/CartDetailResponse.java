package com.bookstore.customer.response;

import com.bookstore.customer.entity.CartDetail;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartDetailResponse {
    private Long id;
    private Long customerId;
    private Long bookId;
    private Long cartItem;
    private String bookName;
    private String image;
    private String autherName;
    private String category;
    private Long discount;
    private Long price;
    private Boolean isDiscount;

    public CartDetailResponse(CartDetail cartDetail){
        this.id = cartDetail.getId();
        this.customerId = cartDetail.getCustomerId();
        this.bookId = cartDetail.getBookId();
        this.cartItem = cartDetail.getCartItem();
    }
}
