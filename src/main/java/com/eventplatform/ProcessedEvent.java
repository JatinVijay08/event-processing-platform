    package com.eventplatform;

    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.Setter;

    import java.math.BigDecimal;
    import java.time.Instant;

    @Entity
    @Getter
    @Setter
    @Table(name = "processed_events")
    public class ProcessedEvent {
        @Id
        private String eventId;

        private String eventType;

        private Integer schemaVersion;

        private Instant occurredAt;

        private String orderId;

        private String customerId;

        private BigDecimal amount;

        private String currency;

        private String status;
    }
