package com.bookstore.customer.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderTrakerCode {

    CONFIRMED("CONFIRMED"),PLACED("PLACED"),SHIPPED("SHIPPED"),
    OUT_FOR_DELIVERY("OUT FOR DELIVERY"),DELIVERED("DELIVERED"),CANCELLED("CANCELLED"),RETURNED("RETURNED");

    private String traker;
}
