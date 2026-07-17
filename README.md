# 🌍 SafarSaathi

<p align="center">

<h3 align="center">
AI-Native Travel Platform powered by Microservices, Spring AI, Event-Driven Architecture, and Cloud-Native Technologies.
</h3>

<p align="center">

Build smarter trips.<br>
Find compatible travel companions.<br>
Interact with an intelligent AI assistant that understands context, plans before responding, and remembers user preferences.

</p>

---

## ✨ Overview

SafarSaathi is a production-grade distributed travel platform designed to demonstrate modern backend engineering practices by combining:

- 🤖 Artificial Intelligence
- ☁️ Cloud-Native Microservices
- 📡 Event-Driven Architecture
- 🔐 Secure Authentication
- 🧠 Long-Term AI Memory
- 🚀 Production-Ready System Design

Unlike traditional travel applications, SafarSaathi includes an AI-native orchestration service capable of:

- Understanding conversation context
- Planning before responding
- Executing multiple backend tools
- Merging platform data
- Remembering long-term user preferences
- Delivering personalized travel assistance

The project focuses on solving real engineering challenges such as distributed system design, AI orchestration, scalability, observability, and cloud deployment instead of simple CRUD operations.

---

# 🚀 Key Features

## 🤖 AI Platform

- AI Chat Assistant
- Context-Aware Conversations
- Conversation History
- Entity Resolution
- Rule-Based Planning Engine
- LLM Planning Engine
- Multi-Tool Execution
- Prompt Enrichment Pipeline
- Long-Term Memory
- Intelligent Memory Extraction
- Personalized AI Responses

---

## 🌍 Travel Platform

- User Management
- Trip Management
- Companion Discovery
- Companion Requests
- Companion Matching
- Real-Time Notifications

---

## ☁️ Distributed System

- API Gateway
- Service Discovery
- JWT Authentication
- Event-Driven Communication
- Kafka Messaging
- Cloud-Native Architecture
- Production-Ready Package Structure

---

# 🏗️ System Architecture

```text
                                        Client
                                           │
                                           ▼
                                ┌─────────────────────┐
                                │    API Gateway      │
                                │      (8081)         │
                                └─────────┬───────────┘
                                          │
          ┌──────────────┬────────────────┼────────────────┬───────────────┐
          │              │                │                │               │
          ▼              ▼                ▼                ▼               ▼

   Auth Service    User Service     Trip Service    Companion Service   AI Service
      (9000)          (9030)           (9040)            (9050)           (9060)
          │              │                │                │               │
          └──────────────┴────────────────┴────────────────┴───────────────┘
                                          │
                                          ▼
                                 Notification Service
                                          │
                                          ▼
                                        Kafka
                                          │
                                          ▼
                                     PostgreSQL
```

---

### Core Components

- API Gateway
- Eureka Discovery Server
- Auth Service
- User Service
- Trip Service
- Companion Service
- Notification Service
- AI Service

---


# 🧠 AI Service Architecture

The AI Service is designed as an orchestration layer rather than a simple chatbot.

Instead of directly sending user messages to the LLM, SafarSaathi first understands the conversation, plans the required actions, executes backend tools, enriches the prompt with platform data, and finally generates a grounded response.

```text
                         User
                          │
                          ▼
                Conversation Context
                          │
                          ▼
                  Entity Resolution
                          │
                          ▼
                 Rule-Based Planner
                          │
                          ▼
                   LLM Planner
                          │
                          ▼
                  Execution Plan
                          │
                          ▼
                  Tool Execution
                          │
                          ▼
                   Context Merger
                          │
                          ▼
                 Prompt Enrichment
                          │
                          ▼
                        Gemini
                          │
                          ▼
                    AI Response
                          │
                          ▼
                   Memory Engine
                          │
                          ▼
                 Long-Term Memory
```

## AI Capabilities

- Context Awareness
- Conversation State
- Entity Resolution
- Rule-Based Planning
- LLM Planning
- Tool Calling
- Prompt Enrichment
- Long-Term Memory
- Intelligent Memory Extraction
- Personalized Responses

---


# 🧩 Microservices Overview

SafarSaathi follows a distributed microservices architecture where every service has a single responsibility and communicates securely through the API Gateway.

| Service | Description | Status |
|----------|-------------|-------|
| API Gateway | Centralized routing, JWT validation, request filtering | 
| Eureka Server | Service registration & discovery |
| Auth Service | Authentication & JWT management |
| User Service | User profiles & preferences |
| Trip Service | Trip planning & management | 
| Companion Service | Companion discovery & matching | 
| Notification Service | Kafka-based notification processing |
| AI Service | AI orchestration, planning & memory |

---

# ⚙️ Technology Stack

## Backend

- Java 21
- Spring Boot 3.3
- Spring Security
- Spring Data JPA
- Spring Cloud Gateway
- Spring Cloud Netflix Eureka
- Spring AI

---

## Artificial Intelligence

- Google Gemini
- Spring AI
- LLM Planning
- Rule-Based Planning
- Tool Calling
- Context Awareness
- Long-Term Memory

---

## Messaging

- Apache Kafka

---

## Databases

- PostgreSQL
- MySQL

---

## Cloud & DevOps

- Docker
- Docker Compose
- Kubernetes
- Helm
- GitHub Actions
- AWS (Planned)

---

## Build Tools

- Maven

---

## Version Control

- Git
- GitHub

---

# 📂 Project Structure

```text
SafarSaathi
│
├── api-gateway/
│
├── discovery-server/
│
├── auth-service/
│
├── user-service/
│
├── trip-service/
│
├── companion-service/
│
├── notification-service/
│
├── ai-service/
│
├── docker/
│
├── kubernetes/
│
├── docs/
│
└── README.md
```

---

# 🔐 API Gateway

The API Gateway acts as the single entry point for all incoming requests.

### Features

- JWT Authentication
- Request Routing
- Authentication Filter
- Route Management
- Centralized Entry Point

### Tech

- Spring Cloud Gateway

---

# 🌐 Eureka Discovery Server

Responsible for dynamic service registration and discovery.

### Features

- Service Registration
- Service Discovery
- Dynamic Instance Resolution

### Tech

- Spring Cloud Netflix Eureka

---

# 🔑 Auth Service

Handles authentication and authorization across the platform.

### Features

- User Registration
- Login
- JWT Generation
- JWT Validation
- Role-Based Authorization
- Secure Authentication Flow

### Tech

- Spring Security
- JWT
- PostgreSQL

---

# 👤 User Service

Responsible for managing user profiles and preferences.

### Features

- User Profile Management
- Profile Updates
- Travel Preferences
- User Lookup

### APIs

```http
GET    /users/profile

GET    /users/profile/{id}

PUT    /users/profile
```

---

# 🧳 Trip Service

Manages travel plans created by users.

### Features

- Create Trips
- Update Trips
- Delete Trips
- Trip Details
- Trip Timeline
- Destination Management
- Budget Information

### APIs

```http
POST   /api/v1/trips

GET    /api/v1/trips

GET    /api/v1/trips/{id}

PUT    /api/v1/trips/{id}

DELETE /api/v1/trips/{id}
```

---

# 🤝 Companion Service

Responsible for connecting compatible travelers.

### Features

- Companion Profile
- Companion Preferences
- Send Requests
- Accept Requests
- Reject Requests
- Companion Recommendations

### APIs

```http
POST   /api/v1/companions

GET    /api/v1/companions

PUT    /api/v1/companions/{id}

DELETE /api/v1/companions/{id}
```

### Companion Request APIs

```http
POST /api/v1/companions/requests

POST /api/v1/companions/requests/{id}/accept

POST /api/v1/companions/requests/{id}/reject

GET  /api/v1/companions/requests/received

GET  /api/v1/companions/requests/sent
```

---

# 🔔 Notification Service

Consumes Kafka events and delivers notifications to users.

### Features

- Kafka Consumer
- Notification Persistence
- Read Notifications
- Unread Notifications
- Mark as Read

### APIs

```http
GET /api/v1/notifications

GET /api/v1/notifications/unread

PUT /api/v1/notifications/{id}/read
```

---

# 🤖 AI Service

The AI Service is the intelligence layer of SafarSaathi.

Unlike traditional chatbot integrations, the AI Service follows an orchestration-first architecture where every user request is analyzed, planned, enriched with platform data, and then forwarded to the LLM.

The AI is capable of understanding conversation context, reasoning over platform data, and remembering long-term user preferences to provide personalized travel assistance.

---

## AI Features

### AI Chat

- Conversational AI powered by Google Gemini
- Multi-turn conversations
- Conversation persistence
- Context-aware responses

---

### Conversation Management

- Conversation History
- Conversation Context
- Conversation State
- Conversation Persistence
- Follow-up Question Handling

---

### Context Awareness

The AI automatically understands the current conversation before generating a response.

Context includes:

- Previous Messages
- Active Conversation
- Current User
- Resolved Entities
- User Memories

---

### Entity Resolution

The AI identifies important entities from user conversations such as:

- Trips
- Destinations
- Companion References
- User References

These entities are reused across future conversation turns.

---

### Rule-Based Planning

Before calling the LLM, SafarSaathi first creates an execution plan.

Instead of directly asking Gemini,

the AI first decides:

- Which backend tools should execute
- What information is required
- How responses should be merged

This dramatically reduces hallucinations and improves response quality.

---

### LLM Planning

The platform also includes an LLM-powered planning layer capable of generating execution plans dynamically.

This architecture allows future support for:

- Dynamic Planning
- AI Travel Concierge
- Multi-Agent Systems
- Retrieval-Augmented Generation (RAG)

without redesigning the system.

---

### Tool Calling Framework

Current tools include:

- User Tool
- Trip Tool
- Companion Tool

Each tool retrieves platform data before the final prompt is generated.

---

### Prompt Enrichment

Before the LLM is called,

the prompt is enriched using:

- Conversation History
- Conversation State
- Platform Data
- Resolved Entities
- User Memories

The LLM therefore receives significantly richer context than a normal chatbot.

---

### Long-Term Memory

SafarSaathi includes persistent AI memory.

The AI automatically remembers meaningful user preferences such as:

- Travel Style
- Food Preferences
- Transportation Preferences
- Language Preferences
- Destination Preferences

These memories are reused in future conversations to deliver personalized travel recommendations.

---

### Intelligent Memory Extraction

The Memory Engine automatically:

- Extracts user preferences
- Validates extracted memories
- Stores long-term memories
- Updates existing memories
- Makes future AI responses personalized

No manual profile updates are required.

---


# 🧠 AI Request Lifecycle

Every AI request follows a structured orchestration pipeline.

```text
                         User Request
                               │
                               ▼
                 Conversation Context Resolver
                               │
                               ▼
                     Conversation State
                               │
                               ▼
                      Entity Resolution
                               │
                               ▼
                        AI Planner
                               │
                               ▼
                     Execution Plan
                               │
                               ▼
                     Tool Execution
                               │
                               ▼
                      Context Merger
                               │
                               ▼
                    Prompt Enrichment
                               │
                               ▼
                        Google Gemini
                               │
                               ▼
                        AI Response
                               │
                               ▼
                        Memory Engine
                               │
                               ▼
                    Persistent Memories
```

---

# 📨 Event-Driven Architecture

SafarSaathi follows an event-driven architecture using Apache Kafka.

Instead of tightly coupling services,

important business events are published to Kafka topics and consumed asynchronously.

## Implemented Events

- Companion Request Received
- Companion Request Accepted
- Companion Request Rejected

---

## Event Flow

```text
Companion Service
        │
        ▼
Publish Event
        │
        ▼
Kafka Topic
        │
        ▼
Notification Service
        │
        ▼
Persist Notification
        │
        ▼
Expose Notification APIs
```

---

## Advantages

- Loose Coupling
- Better Scalability
- Asynchronous Processing
- Fault Isolation
- Improved System Reliability

---

# 🔐 Security Architecture

Authentication is centralized using JWT Authentication.

```text
User Login
      │
      ▼
Auth Service
      │
      ▼
Generate JWT
      │
      ▼
API Gateway
      │
      ▼
Authentication Filter
      │
      ▼
Forward Request
      │
      ▼
Microservices
```

### Security Features

- JWT Authentication
- Role-Based Authorization
- API Gateway Authentication Filter
- User Context Propagation
- Secure Service Communication

---

# 📈 Project Status

##  Completed

### Backend

- API Gateway
- Eureka Discovery Server
- JWT Authentication
- Auth Service
- User Service
- Trip Service
- Companion Service
- Notification Service

### AI Service

- AI Chat
- Conversation Management
- Context Awareness
- Entity Resolution
- Rule-Based Planner
- LLM Planner
- Tool Calling Framework
- Context Merger
- Prompt Enrichment
- Long-Term Memory
- Intelligent Memory Extraction

### Architecture

- Microservices
- Event-Driven Communication
- Kafka Integration

---


### DevOpa and Cloud

- Docker
- Kubernetes
- AWS Deployment
- Monitoring
- CI/CD
- AI Service v2
