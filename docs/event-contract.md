# Order Event Contract

## 1. Purpose

Define the message format used by the producer to publish order-created events to Kafka and by the consumer to process them.

Kafka topic: `order-events`

Initial event type: `ORDER_CREATED`

Initial schema version: `1`

## 2. Example event

```json
{
  "eventId": "7a2c1a8e-0f5c-4d11-9c23-123456789abc",
  "eventType": "ORDER_CREATED",
  "schemaVersion": 1,
  "occurredAt": "2026-10-13T18:30:00Z",
  "payload": {
    "orderId": "order-123",
    "customerId": "customer-456",
    "amount": 499.0,
    "currency": "INR"
  }
}
```

This is an illustrative example. IDs and timestamps will be generated at runtime.

## 3. Field definitions

### Event envelope

| Field           | Type                   | Required | Meaning                                 |
| --------------- | ---------------------- | -------- | --------------------------------------- |
| `eventId`       | String                 | Yes      | Unique identifier for this event        |
| `eventType`     | String                 | Yes      | Name of the event                       |
| `schemaVersion` | Integer                | Yes      | Version of the event schema             |
| `occurredAt`    | ISO 8601 UTC timestamp | Yes      | Time the event occurred                 |
| `payload`       | Object                 | Yes      | Business data associated with the event |

### Payload

| Field        | Type           | Required | Meaning                        |
| ------------ | -------------- | -------- | ------------------------------ |
| `orderId`    | String         | Yes      | Identifier of the order        |
| `customerId` | String         | Yes      | Identifier of the customer     |
| `amount`     | Decimal number | Yes      | Non-negative order amount      |
| `currency`   | String         | Yes      | Currency code; initially `INR` |

In Java, monetary values will use `BigDecimal` rather than binary floating-point types.

## 4. Event identity

`eventId` identifies one event occurrence. It is distinct from `orderId`.

An order may generate multiple events over time. Redelivery of the same event must retain the same event ID.

The consumer will use event identity to detect duplicate processing. A unique database constraint will provide a final safeguard against duplicate rows.

## 5. Kafka record key

The initial record key will be `orderId`.

This makes records for the same order eligible to be routed to the same partition under the default keyed partitioning strategy, helping preserve their partition order.

This does not create a global ordering guarantee across all orders or across multiple partitions.

## 6. Validation rules

- `eventId` must be present and non-empty.
- `eventType` must be `ORDER_CREATED` for this version.
- `schemaVersion` must be `1`.
- `occurredAt` must be a valid UTC timestamp.
- `orderId` and `customerId` must be present and non-empty.
- `amount` must be non-negative.
- `currency` must be `INR` in the initial version.

Invalid HTTP requests should be rejected before publishing an event. Invalid or malformed records encountered by the consumer require explicit failure handling.

## 7. Processing outcome

The consumer stores a record representing the event and its processing status.

Initial statuses:

- `PROCESSED`: processing completed and the database transaction committed.
- `FAILED`: processing reached a terminal failure state, if and when terminal failure tracking is implemented.

Retry attempts and dead-letter routing will be introduced in the reliability iteration.

## 8. Compatibility and evolution

Any incompatible change to the event structure requires careful coordination between producer and consumer.

`schemaVersion` is included so future versions can be identified explicitly. Versioning alone does not make schema changes backward-compatible.

For the initial project, version 1 is the only supported schema.

## 9. Delivery and idempotency

Kafka records can be delivered to an application more than once, depending on processing and offset-commit timing.

The consumer must therefore tolerate duplicate event delivery. Database writes and event ID uniqueness will be designed together to prevent repeated processing from creating duplicate event rows.

This contract does not promise end-to-end exactly-once processing.
