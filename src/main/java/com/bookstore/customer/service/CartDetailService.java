package com.bookstore.customer.service;

import com.bookstore.customer.request.CartDetailRequest;
import com.bookstore.customer.response.CartDetailResponse;
import com.bookstore.customer.response.CommonResponse;

import java.util.List;

public interface CartDetailService {
    CommonResponse<String> saveCartDetail(CartDetailRequest cartDetailRequest);

    CommonResponse<String> updateCartDetail(CartDetailRequest cartDetailRequest);

    CommonResponse<List<CartDetailResponse>> findAllCartItems();

    CommonResponse<String> deleteCartItem(Long id);
}
