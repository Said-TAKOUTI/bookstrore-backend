package com.takouti.bookstore.dto;

import com.takouti.bookstore.entity.*;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Orders orders;
    private Set<OrderItem> orderItems;
}
