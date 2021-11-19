package com.dapr.spring.pubsub.controller;

import com.dapr.spring.pubsub.pojo.Subscription;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class DaprPubSubController {

    /**
     * To subscribe to topics, start a web server and listen on
     * the following GET endpoint: /dapr/subscribe.
     * The Dapr instance calls into your app at startup and expect a JSON response for
     * the topic subscriptions with:
     *
     * @return List<Subscription>
     */
    @GetMapping("/dapr/subscribe")
    public List<Subscription> subscribe() {
        /*
         * pubsubname: Which pub/sub component Dapr should use.
         * topic: Which topic to subscribe to.
         * route: Which endpoint for Dapr to call on when a message comes to that topic.
         */
        Subscription subscription = new Subscription("pubsub", "A", "processors/A");
        return List.of(subscription);
    }
}
