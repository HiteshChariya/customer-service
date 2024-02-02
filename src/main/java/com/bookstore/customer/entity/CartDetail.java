package com.bookstore.customer.entity;

import com.bookstore.customer.auth.TokenData;
import com.bookstore.customer.constant.Constants;
import com.bookstore.customer.request.CartDetailRequest;
import com.bookstore.customer.response.BookDetailResponse;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "cart", schema = Constants.DATA_REVIEW_SCHEMA)
@Getter
public class CartDetail extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;
    private Long customerId;
    private Long bookId;
    private Long cartItem;
    private Long orderStatusId;

    public void saveCartDetail(CartDetail cartDetail, CartDetailRequest cartDetailRequest, TokenData tokenData, String recordStatus, String source) {
        cartDetail.customerId = tokenData.getId();
        cartDetail.bookId = cartDetailRequest.getBookId();
        cartDetail.cartItem = cartDetailRequest.getCartItem();
        cartDetail.orderStatusId = 0L;
        if(id == null)
            cartDetail.create(recordStatus,tokenData.getFirstName(),source);
        else
            cartDetail.update(recordStatus,tokenData.getFirstName());
    }

    public void updateCart(CartDetail cartDetail,String shortCode, TokenData tokenData,Long orderStatusId){
        cartDetail.orderStatusId = orderStatusId;
        cartDetail.update(shortCode,tokenData.getFirstName());
    }

    public void deleteCartDetail(CartDetail cartDetail, String shortCode, TokenData tokenData) {
        cartDetail.update(shortCode,tokenData.getFirstName());
    }
}
