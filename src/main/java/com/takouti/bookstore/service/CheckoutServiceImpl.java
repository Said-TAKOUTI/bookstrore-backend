package com.takouti.bookstore.service;

import com.takouti.bookstore.dao.CustomerRepository;
import com.takouti.bookstore.dto.Purchase;
import com.takouti.bookstore.dto.PurchaseResponse;
import com.takouti.bookstore.entity.Customer;
import com.takouti.bookstore.entity.OrderItem;
import com.takouti.bookstore.entity.Orders;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final CustomerRepository customerRepository;

    public CheckoutServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        // retrieve the order info from dto
        Orders orders = purchase.getOrders();

        // generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        if (orders == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        orders.setOrderTrackingNumber(orderTrackingNumber);

        // populate order with orderItems
        Set<OrderItem> orderItems = orders.getOrderItems();
        orderItems.forEach(orderItem -> orders.addOrderItem(orderItem));

        //populate order with billingAddress and shippingAddress
        orders.setBillingAddress(purchase.getBillingAddress());
        orders.setShippingAddress(purchase.getShippingAddress());

        //populate customer with order
        Customer customer = purchase.getCustomer();

        // check if this is an existing customer
        String theEmail = customer.getEmail();

        Customer customerFromDB = customerRepository.findByEmail(theEmail);

        if (customerFromDB != null) {
            // we found them ... let's assign them accordingly
            customer = customerFromDB;
        }
        customer.addOrder(orders);

        //save to the database
        customerRepository.save(customer);

        // return a response
        return new PurchaseResponse(orderTrackingNumber);

    }

    private String generateOrderTrackingNumber() {

        // generate a random UUID number (UUID version-4)
        return UUID.randomUUID().toString();
    }
}
