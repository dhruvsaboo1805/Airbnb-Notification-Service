# Airbnb Notification Service

A production-style event-driven notification microservice built using **Spring Boot**, **Redis**, **AWS SNS**, and **Docker**.

This service listens to booking saga events from Redis queues and sends asynchronous notifications to users via AWS SNS.

---

# 🚀 Features

- Event-driven notification architecture
- Redis queue consumer
- AWS SNS integration
- Booking confirmation notifications
- Booking cancellation/refund notifications
- Dockerized deployment
- EC2 compatible
- Microservice-based architecture
- Decoupled from core booking logic

---

# 🏗️ Architecture

```text
Airbnb Booking Service
        ↓
Saga Event Publisher
        ↓

┌──────────────────────────┐
│ Redis Queues             │
│                          │
│ notification:events      │
└──────────────────────────┘
        ↓

Notification Service
        ↓
AWS SNS
        ↓
Email / SMS Notifications
