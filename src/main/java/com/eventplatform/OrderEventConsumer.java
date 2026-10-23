package com.eventplatform;

import com.eventplatform.events.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventConsumer {

    private final  ProcessedEventRepository processedEventRepository;
    public OrderEventConsumer(ProcessedEventRepository processedEventRepository) {
        this.processedEventRepository = processedEventRepository;
    }

    @KafkaListener(topics = "order-events" , groupId = "order-event-processors")
    public void listenEvent(OrderEvent orderEvent) {
        ProcessedEvent processedEvent = new ProcessedEvent();
        processedEvent.setEventType(orderEvent.getEventType());
        processedEvent.setEventId(orderEvent.getEventId());
        processedEvent.setOrderId(orderEvent.getPayload().getOrderId());
        processedEvent.setAmount(orderEvent.getPayload().getAmount());
        processedEvent.setCustomerId(orderEvent.getPayload().getCustomerId());
        processedEvent.setSchemaVersion(orderEvent.getSchemaVersion());
        processedEvent.setStatus("PROCESSED");
        processedEvent.setOccurredAt(orderEvent.getOccurredAt());
        processedEvent.setCurrency(orderEvent.getPayload().getCurrency());

        processedEventRepository.save(processedEvent);

        System.out.println("Received order event: "  + orderEvent.toString());
    }
}
