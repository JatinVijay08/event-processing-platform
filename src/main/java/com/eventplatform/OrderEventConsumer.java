package com.eventplatform;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventConsumer {

    @KafkaListener(topics = "order-events" , groupId = "order-event-processors")
    public void listenEvent(String message) {
        System.out.println("Received order event: " + message);
    }
}
