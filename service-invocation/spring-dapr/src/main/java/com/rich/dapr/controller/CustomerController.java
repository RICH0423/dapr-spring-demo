package com.rich.dapr.controller;

import com.rich.dapr.client.OrderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/customers")
@RestController
public class CustomerController {

    OrderClient orderClient;

    @Autowired
    public CustomerController(OrderClient orderClient) {
        this.orderClient = orderClient;
    }

    @GetMapping(value = "/orders")
    public String getOrders() {
        return orderClient.getOrders();
    }

}