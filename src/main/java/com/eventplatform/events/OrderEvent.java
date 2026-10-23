package com.eventplatform.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent {
    private String eventId;

    private String eventType;

    private Integer schemaVersion;

    private Instant occurredAt;

    private OrderPayload payload;
}
