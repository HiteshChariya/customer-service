package com.bookstore.customer.entity;

import com.bookstore.customer.constant.Constants;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "order_traker", schema = Constants.DATA_REVIEW_SCHEMA)
@Getter
public class OrderTrakerStatus extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_traker_id")
    private Long id;

    @Column(length = 50)
    private String orderTrakerStatue;
    @Column(length = 100)
    private String description;
}
