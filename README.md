
# Event Processing Platform

A Java and Spring Boot learning project exploring asynchronous event processing using Apache Kafka and PostgreSQL.

## Architecture

The system accepts order requests, publishes order-created events to Kafka, processes events asynchronously, and persists processing results in PostgreSQL.

See:
- [Architecture](docs/architecture.md)
- [Event Contract](docs/event-contract.md)

## Technology Stack

- Java
- Spring Boot
- Apache Kafka
- PostgreSQL
- Spring Data JPA
- Maven
- Docker Compose
- JUnit

## Current Status

- [x] Initial architecture documented
- [x] Event contract defined
- [x] Spring Boot project initialized
- [ ] Local Kafka environment
- [ ] Order producer API
- [ ] Consumer and database persistence
- [ ] Duplicate handling and failure recovery
- [ ] Integration tests

## Running Locally

Prerequisite: a compatible JDK.

Run tests:

```powershell
.\mvnw.cmd test
```

Start the application:

```powershell
.\mvnw.cmd spring-boot:run
```

Kafka and PostgreSQL setup will be documented as the infrastructure is added.
