package com.rich.dapr.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "order-client", url = "${dapr.orderUrl}")
public interface OrderClient {

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    String getOrders();
}

