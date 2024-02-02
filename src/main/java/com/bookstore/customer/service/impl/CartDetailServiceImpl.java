package com.bookstore.customer.service.impl;

import com.bookstore.customer.auth.FetchTokenData;
import com.bookstore.customer.auth.TokenData;
import com.bookstore.customer.constant.CustomerConstants;
import com.bookstore.customer.constant.MessageConstant;
import com.bookstore.customer.entity.CartDetail;
import com.bookstore.customer.enums.RecordSourceCode;
import com.bookstore.customer.enums.RecordStatusCode;
import com.bookstore.customer.feign.BookClientService;
import com.bookstore.customer.repository.CartDetailServiceRepository;
import com.bookstore.customer.request.CartDetailRequest;
import com.bookstore.customer.response.BookDetailResponse;
import com.bookstore.customer.response.CartDetailResponse;
import com.bookstore.customer.response.CommonResponse;
import com.bookstore.customer.service.CartDetailService;
import com.bookstore.customer.service.FiengClientService;
import com.bookstore.customer.utils.MessageSupplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CartDetailServiceImpl implements CartDetailService {

    @Autowired
    private MessageSupplier messageSupplier;

    @Autowired
    private FetchTokenData fetchTokenData;

    @Autowired
    private CartDetailServiceRepository cartDetailServiceRepository;

    @Autowired
    FiengClientService fiengClientService;

    @Override
    public CommonResponse<String> saveCartDetail(CartDetailRequest cartDetailRequest) {
        log.info("Save Cart Detail Service...");
        TokenData tokenData = fetchTokenData.getTokenData();
        String recordStatus = RecordStatusCode.ENABLED.getShortCode();
        String source = RecordSourceCode.ECP_WB.getShortName();

        Optional<CartDetail> optCartDetail = cartDetailServiceRepository.findByIdAndRecordStatusCode(cartDetailRequest.getCartId(),recordStatus);
        if(optCartDetail.isPresent()){
            return CommonResponse.success(messageSupplier.get(MessageConstant.SHORT_NAME_ALREADY_EXITST));
        }

        CartDetail cartDetail = new CartDetail();
        cartDetail.saveCartDetail(cartDetail,cartDetailRequest,tokenData,recordStatus,source);
        cartDetailServiceRepository.save(cartDetail);
        return CommonResponse.success(messageSupplier.get(CustomerConstants.CartDetailConstant.CART_DETAIL_SAVE_SUCCESS));
    }

    @Override
    public CommonResponse<String> updateCartDetail(CartDetailRequest cartDetailRequest) {
        log.info("Save Cart Detail Service...");
        TokenData tokenData = fetchTokenData.getTokenData();
        String recordStatus = RecordStatusCode.ENABLED.getShortCode();
        String source = RecordSourceCode.ECP_WB.getShortName();

        Optional<CartDetail> optCartDetail = cartDetailServiceRepository.findByIdAndRecordStatusCode(cartDetailRequest.getCartId(),recordStatus);
        if(optCartDetail.isPresent()){
            CartDetail updateCartDetail = optCartDetail.get();
            updateCartDetail.saveCartDetail(updateCartDetail,cartDetailRequest,tokenData,recordStatus,source);
            cartDetailServiceRepository.save(updateCartDetail);
            return CommonResponse.success(messageSupplier.get(CustomerConstants.CartDetailConstant.CART_DETAIL_UPDATE_SUCCESS));
        }
        return CommonResponse.success(messageSupplier.get(CustomerConstants.CartDetailConstant.INVALID_CART_DETAIL_ID));
    }

    @Override
    public CommonResponse<List<CartDetailResponse>> findAllCartItems() {
        log.info("Find Cart Detail Service...");
        Long orderStatusId = 0L;
        TokenData tokenData = fetchTokenData.getTokenData();
        List<CartDetailResponse> cartResponse = new ArrayList<>();
        List<CartDetail> cartItems = cartDetailServiceRepository
                .findByCustomerIdAndOrderStatusIdAndRecordStatusCode(tokenData.getId(),orderStatusId,RecordStatusCode.ENABLED.getShortCode());
        if (cartItems.isEmpty())
            return CommonResponse.success(messageSupplier.get(CustomerConstants.CartDetailConstant.CART_DETAIL_FETCH_SUCCESS),cartResponse);

        cartResponse = cartItems.stream().map(m -> new CartDetailResponse(m))
                .collect(Collectors.toList());

        cartResponse.forEach(cart ->{
            BookDetailResponse bookDetailResponse = fiengClientService.findBookById(cart.getBookId());
            cart.setBookName(bookDetailResponse.getBookName());
            cart.setImage(bookDetailResponse.getImage());
            cart.setAutherName(bookDetailResponse.getAuthor());
            cart.setCategory(bookDetailResponse.getCategory());
            cart.setDiscount(bookDetailResponse.getDiscount());
            cart.setPrice(bookDetailResponse.getPrice());
            cart.setIsDiscount(bookDetailResponse.getIsDiscount());
        });

        return CommonResponse.success(messageSupplier.get(CustomerConstants.CartDetailConstant.CART_DETAIL_FETCH_SUCCESS),
                cartResponse);
    }

    @Override
    public CommonResponse<String> deleteCartItem(Long id) {
        log.info("Delete Book Detail service....");
        TokenData tokenData = fetchTokenData.getTokenData();
        Optional<CartDetail> optCart = cartDetailServiceRepository.findById(id);
        if (optCart.isPresent()) {
            CartDetail cartDetail = optCart.get();
            cartDetail.deleteCartDetail(cartDetail, RecordStatusCode.DISABLED.getShortCode(),tokenData);
            cartDetailServiceRepository.save(cartDetail);
            return CommonResponse.success(messageSupplier.get(CustomerConstants.CartDetailConstant.CART_DETAIL_DELETE_SUCCESS, id));
        }
        return CommonResponse.failure(messageSupplier.get(CustomerConstants.CartDetailConstant.INVALID_CART_DETAIL_ID, id));
    }

}
