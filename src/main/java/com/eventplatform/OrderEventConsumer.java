package com.eventplatform;

import com.eventplatform.events.OrderEvent;
import org.hibernate.query.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventConsumer {

    @KafkaListener(topics = "order-events" , groupId = "order-event-processors")
    public void listenEvent(OrderEvent orderEvent) {
        System.out.println("Received order event: "  + orderEvent.toString());
    }
}
