package com.takouti.bookstore.service;

import com.takouti.bookstore.dto.Purchase;
import com.takouti.bookstore.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
