package com.takouti.bookstore.service;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.takouti.bookstore.dto.PaymentInfo;
import com.takouti.bookstore.dto.Purchase;
import com.takouti.bookstore.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);

    PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException;
}
