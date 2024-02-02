package com.bookstore.customer.controller;

import com.bookstore.customer.request.AddressRequest;
import com.bookstore.customer.request.CartDetailRequest;
import com.bookstore.customer.response.CartDetailResponse;
import com.bookstore.customer.response.CommonResponse;
import com.bookstore.customer.service.CartDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@Tag(name = "Cart Configuration API", description = "Cart Configuration API for BookStore Managemant")
@Slf4j
@CrossOrigin("http://localhost/3000")
public class CartDetailController {

    @Autowired
    private CartDetailService cartDetailService;

    @Operation(summary = "Save Cart Details", description = "Save Cart Details")
    @PostMapping("/cart")
    public CommonResponse<String> saveCartDetail(@RequestBody CartDetailRequest cartDetailRequest) {
        log.info("Save Cart Detail {} ",cartDetailRequest);
        return cartDetailService.saveCartDetail(cartDetailRequest);
    }

    @Operation(summary = "Update Cart Details", description = "update Cart Details")
    @PostMapping("/cart/edit")
    public CommonResponse<String> updateCartDetail(@RequestBody CartDetailRequest cartDetailRequest) {
        log.info("update Cart Detail {} ",cartDetailRequest);
        return cartDetailService.updateCartDetail(cartDetailRequest);
    }

    @Operation(summary = "Get List of Cart Items", description = "Get List of Cart Items")
    @GetMapping("/cart/all")
    public CommonResponse<List<CartDetailResponse>> findAllCartItems(){
        log.info("Get All Cart Items");
        return cartDetailService.findAllCartItems();
    }

    @Operation(summary = "Delete Cart Item", description = "Delete Cart Item")
    @PatchMapping("/cart/{id}")
    public CommonResponse<String> deleteCartItem(@PathVariable("id") Long id){
        log.info("Delete Cart Item By Id {} ",id);
        return cartDetailService.deleteCartItem(id);
    }
}
