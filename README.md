# 🌍 SafarSaathi

> AI-Native Travel Companion Platform built using Microservices, Event-Driven Architecture, Kafka, Spring Boot, and Cloud-Native Technologies.

---

## 📖 Overview

SafarSaathi is a microservices-based travel companion platform that helps travelers discover trips, connect with compatible travel companions, and receive real-time notifications throughout their journey.

The project is being built as a production-grade distributed system with a strong focus on:

* Microservices Architecture
* Event-Driven Communication
* Service Discovery
* API Gateway Pattern
* JWT Authentication & Authorization
* Kafka Messaging
* Cloud-Native Deployment
* Observability
* AI-Powered Companion Matching (Upcoming)

---

# 🏗️ Current Architecture

```text
                        ┌─────────────────┐
                        │   API Gateway   │
                        │    Port 8081    │
                        └────────┬────────┘
                                 │
         ┌───────────────────────┼───────────────────────┐
         │                       │                       │
         ▼                       ▼                       ▼

 ┌───────────────┐      ┌────────────────┐     ┌─────────────────┐
 │ Auth Service  │      │ User Service   │     │ Companion Service│
 │ JWT Security  │      │ User Profiles  │     │ Matching & Reqs │
 └───────┬───────┘      └────────────────┘     └────────┬────────┘
         │                                               │
         │                                               ▼
         │                                    ┌─────────────────┐
         │                                    │ Kafka Producer  │
         │                                    └────────┬────────┘
         │                                             │
         ▼                                             ▼

                 ┌─────────────────────────────────┐
                 │             Kafka               │
                 └────────────────┬────────────────┘
                                  │
                                  ▼

                     ┌─────────────────────────┐
                     │ Notification Service    │
                     │ Kafka Consumer          │
                     │ Notification APIs       │
                     └─────────────────────────┘

                                  │
                                  ▼

                           PostgreSQL
```

---

# 🚀 Implemented Services

---

## 1️⃣ API Gateway

### Features

* Centralized Entry Point
* JWT Validation
* Route Management
* Service-to-Service Routing
* Authentication Filter

### Technology

* Spring Cloud Gateway

---

## 2️⃣ Eureka Discovery Server

### Features

* Service Registration
* Service Discovery
* Dynamic Instance Resolution

### Technology

* Spring Cloud Netflix Eureka

---

## 3️⃣ Auth Service

### Features

* User Registration
* User Login
* JWT Token Generation
* JWT Validation
* Role-Based Authentication

### Technology

* Spring Boot
* Spring Security
* JWT

---

## 4️⃣ User Service

### Features

* User Profile Management
* Profile Update
* User Lookup
* Feign Client Integration Support

### APIs

```http
GET /users/profile

GET /users/profile/{userId}

PUT /users/profile
```

---

## 5️⃣ Companion Service

### Features

* Create Companion Profile
* Companion Preferences
* Send Companion Requests
* Accept Companion Requests
* Reject Companion Requests
* Feign Client Integration with User Service

### APIs

```http
POST /api/v1/companions

PUT /api/v1/companions/{id}

DELETE /api/v1/companions/{id}

GET /api/v1/companions
```

### Companion Request APIs

```http
POST /api/v1/companions/requests

POST /api/v1/companions/requests/{id}/accept

POST /api/v1/companions/requests/{id}/reject

GET /api/v1/companions/requests/received

GET /api/v1/companions/requests/sent
```

---

## 6️⃣ Notification Service

### Features

* Kafka Consumer
* Event Persistence
* Notification Retrieval
* Unread Notifications
* Mark Notifications as Read

### Event Flow

```text
Companion Service
        ↓
Notification Event
        ↓
Kafka Topic
        ↓
Notification Service
        ↓
PostgreSQL
```

### APIs

```http
GET /api/v1/notifications

GET /api/v1/notifications/unread

PUT /api/v1/notifications/{id}/read
```

---

# 🔄 Event-Driven Architecture

Currently Implemented Events:

### REQUEST_RECEIVED

Triggered when a user receives a companion request.

### REQUEST_ACCEPTED

Triggered when a companion request is accepted.

### REQUEST_REJECTED

Triggered when a companion request is rejected.

---

# 📨 Kafka Integration

### Producer

Companion Service

### Consumer

Notification Service

### Topic

```text
notification-topic
```

---

# 🔐 Security

Authentication is handled centrally using JWT.

### Flow

```text
User Login
      ↓
JWT Token
      ↓
API Gateway
      ↓
Authentication Filter
      ↓
Microservices
```

---

# 🛠️ Tech Stack

## Backend

* Java 21
* Spring Boot 3.3
* Spring Security
* Spring Data JPA
* Spring Cloud

## Communication

* OpenFeign
* Apache Kafka

## Databases

* PostgreSQL

## Service Discovery

* Eureka Server

## API Gateway

* Spring Cloud Gateway

## Build Tool

* Maven

## Version Control

* Git
* GitHub

---

# 📂 Current Service Ports

| Service              | Port |
| -------------------- | ---- |
| API Gateway          | 8081 |
| Eureka Server        | 8761 |
| Auth Service         | 9000 |
| User Service         | 9030 |
| Companion Service    | 9040 |
| Notification Service | 9050 |

---

# 📌 Current Progress

### Completed

* API Gateway
* Eureka Discovery
* JWT Authentication
* User Management
* Companion Management
* Companion Requests
* Feign Client Integration
* Kafka Event Publishing
* Kafka Event Consumption
* Notification Service

### In Progress

* Trip Service

### Planned

* Companion Matching Engine
* Neo4j Recommendation Graph
* Redis Caching
* Prometheus Monitoring
* Grafana Dashboards
* Distributed Tracing
* Docker Compose
* Kubernetes Deployment
* AWS EKS
* GitHub Actions CI/CD
* ArgoCD GitOps
* AI Companion Recommendation Engine
* Multi-Agent Travel Assistant

---

# 👨‍💻 Author

**Yash Chauhan**

Building production-grade distributed systems with Java, Spring Boot, Kafka, Kubernetes, Cloud, and AI-powered architectures.

GitHub:
https://github.com/yashdotdev13

LinkedIn:
https://www.linkedin.com/in/yash-chauhan-a415b6246/

---

## ⭐ SafarSaathi Vision

SafarSaathi is evolving into an AI-Native Travel Platform where travelers can:

* Discover trips
* Find compatible companions
* Receive intelligent recommendations
* Interact with AI travel agents
* Build trusted travel networks

The goal is to combine:

**Backend Engineering + Distributed Systems + Cloud + DevOps + AI**
