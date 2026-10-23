package com.eventplatform;

import com.eventplatform.events.OrderEvent;
import com.eventplatform.events.OrderPayload;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTestMessage(){
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setEventType("ORDER_CREATED");
        orderEvent.setEventId(UUID.randomUUID().toString());
        orderEvent.setOccurredAt(Instant.now());
        orderEvent.setSchemaVersion(1);
        orderEvent.setPayload(new OrderPayload("order1","customer1",new BigDecimal("499.99"),"INR"));
        kafkaTemplate.send("order-events","order1",orderEvent);
    }



}
