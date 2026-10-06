# CareerOrbit — AI-Powered Job Portal Backend (Spring Boot Microservices)

A state-of-the-art, enterprise-grade **AI-powered job portal backend platform** built using a microservices architecture with **Java 21**, **Spring Boot 4**, **Spring Cloud**, **Apache Kafka**, **PostgreSQL**, and **Google Gemini AI**.

Supports centralized configuration management, dynamic service discovery, unified API gateway routing, asynchronous event-driven notifications, AI-assisted candidate resume matching, and separate database-per-service isolation.

---

## 🧱 Tech Stack

### ⚙️ Core Backend & Frameworks
- **Java 21**
- **Spring Boot 4.1.0**
- **Spring Cloud 2025.1.2** (Gateway, Eureka, Config Server)
- **Spring Data JPA & Hibernate**
- **Spring Security & JJWT (JSON Web Token)**
- **Spring Kafka & Spring Mail**
- **Lombok**

### 🤖 AI Integration
- **Google GenAI SDK** (`com.google.genai:google-genai:1.0.0`) powered by **Google Gemini API**

### 🛢️ Databases & Messaging
- **PostgreSQL 16** (Database-per-service pattern for complete microservice decoupling)
- **Apache Kafka 4.1.1** (Event-driven asynchronous messaging broker)

### 🐋 Containerization & Infrastructure
- **Docker & Docker Compose** (Full orchestration for databases, infra, and services)
- **Google Jib Maven Plugin** (Daemonless container image creation)

---

## 📁 Project Structure

```text
CareerOrbit Project/
│
├── careerorbit-backend/                # Microservices Maven Multi-Module Root
│   ├── cloud/                          # Infrastructure & Service Discovery
│   │   ├── careerorbit-api-gateway     # Unified Routing & Security Entry Point (Port 5555)
│   │   ├── careerorbit-config-server   # Centralized Git-backed Config Server (Port 8888)
│   │   └── careerorbit-service-registry# Netflix Eureka Service Discovery (Port 8761)
│   │
│   ├── services/                       # Business Domain Microservices
│   │   ├── careerorbit-user-service    # Authentication, JWT & User Profiles (Port 8001)
│   │   ├── careerorbit-company-service # Employer & Company Profiles (Port 8002)
│   │   ├── careerorbit-job-service     # Job Listings, Search & Metadata (Port 8003)
│   │   ├── careerorbit-resume-service  # Resume Uploads & Candidate Profiles (Port 8004)
│   │   ├── careerorbit-application-service # Job Application Workflows (Port 8005)
│   │   ├── careerorbit-preference-service  # Candidate Job Preferences & Targets (Port 8006)
│   │   ├── careerorbit-ai-service         # Gemini AI Resume & Job Matching (Port 8007)
│   │   └── careerorbit-notification-service # Kafka-driven Email Notifications (Port 8008)
│   │
│   ├── common-lib/                     # Shared DTOs, Security Utils & Event Payload Schemas
│   └── pom.xml                         # Parent Maven POM
│
├── careerorbit-docker/                 # Docker Orchestration Configuration
│   ├── docker-compose.yml              # Complete Multi-Service Stack Setup
│   ├── docker-compose.dev.yml          # Standalone Infrastructure Setup
│   └── .env.example                    # Environment Variables Template
│
└── README.md
```

---

## 🧩 Microservices Overview

| Service Name | Port | Description | Database |
| ------------ | ---- | ----------- | -------- |
| **Service Registry** | `8761` | Netflix Eureka Service Discovery Server | N/A |
| **Config Server** | `8888` | Centralized Spring Cloud Config Server | N/A |
| **API Gateway** | `5555` | Unified entry point for API routing & security filters | N/A |
| **User Service** | `8001` | Auth, JWT issuing, user account management | `careerorbit_user` (5433) |
| **Company Service** | `8002` | Employer profile management & company branding | `careerorbit_company` (5434) |
| **Job Service** | `8003` | Job posting management, categorization & queries | `careerorbit_job` (5435) |
| **Resume Service** | `8004` | Resume processing and candidate data management | `careerorbit_resume` (5438) |
| **Application Service** | `8005` | Job application submissions and candidate tracking | `careerorbit_application` (5436) |
| **Preference Service** | `8006` | Candidate job alerts and location/role preferences | `careerorbit_preference` (5437) |
| **AI Service** | `8007` | Google Gemini AI integration for smart recommendations | N/A |
| **Notification Service** | `8008` | Kafka consumer for sending automated email notifications | N/A |

---

## 🔗 Architecture & Communication Flow

1. **Service Discovery & Configuration:**
   - On startup, microservices fetch central configuration from **Spring Cloud Config Server** (`8888`) and register themselves with **Netflix Eureka Service Registry** (`8761`).

2. **Unified Client Ingress:**
   - Client requests are sent through **Spring Cloud Gateway** (`5555`), which handles dynamic service routing, JWT validation, and load balancing across registered service instances.

3. **Database Isolation:**
   - Each business service maintains strict domain boundaries with a dedicated PostgreSQL database container (Database-Per-Service pattern).

4. **Event-Driven Asynchronous Messaging:**
   - Core domain actions (e.g. user registrations, application updates) publish event payloads to **Apache Kafka**.
   - The **Notification Service** consumes these topics asynchronously to trigger emails via Spring Mail.

5. **AI-Powered Capabilities:**
   - The **AI Service** leverages the official **Google GenAI SDK** to generate smart candidate insights, resume evaluations, and job matching scores via Gemini models.

---

## 🔑 Environment Configuration

Create a `.env` file in the `careerorbit-docker` directory using `.env.example` as a template:

```env
CAREERORBIT_SECURITY_JWT_SECRET=your_jwt_secret_key
CAREERORBIT_SECURITY_JWT_ISSUER=careerorbit

CAREERORBIT_POSTGRES_DB_PASSWORD=your_db_password
CAREERORBIT_GEMINI_API_KEY=your_google_gemini_api_key
CAREERORBIT_GIT_TOKEN=your_github_token_for_config_repo

CAREERORBIT_MAIL_HOST=smtp.gmail.com
CAREERORBIT_MAIL_PORT=587
CAREERORBIT_MAIL_USERNAME=your_email@gmail.com
CAREERORBIT_MAIL_PASSWORD=your_email_app_password

CAREERORBIT_KAFKA_BOOTSTRAP_SERVERS=kafka:9092
```

---

## ⚙️ Setup & Execution

### 🧩 Prerequisites

- **Java 21 SDK**
- **Maven 3.9+**
- **Docker & Docker Compose**
- **Git**

---

### 🚀 Option 1: Running with Docker Compose (Recommended)

1. Navigate to the Docker configuration directory:
   ```bash
   cd careerorbit-docker
   ```

2. Copy `.env.example` to `.env` and fill in your secrets:
   ```bash
   cp .env.example .env
   ```

3. Launch all database containers, infrastructure components, and microservices:
   ```bash
   docker-compose up -d
   ```

📍 Service Registry Dashboard: **http://localhost:8761**  
📍 API Gateway Entry Point: **http://localhost:5555**

---

### 🧰 Option 2: Running Locally via Maven

1. Navigate to the backend root directory:
   ```bash
   cd careerorbit-backend
   ```

2. Start local infrastructure services (PostgreSQL & Kafka) via Docker:
   ```bash
   cd ../careerorbit-docker
   docker-compose -f docker-compose.dev.yml up -d
   ```

3. Build all modules and install the shared library:
   ```bash
   cd ../careerorbit-backend
   mvn clean install -DskipTests
   ```

4. Launch services in sequence:
   - **Service Registry**: `mvn spring-boot:run -pl cloud/careerorbit-service-registry`
   - **Config Server**: `mvn spring-boot:run -pl cloud/careerorbit-config-server`
   - **API Gateway**: `mvn spring-boot:run -pl cloud/careerorbit-api-gateway`
   - **Domain Services**: Run desired services from `services/` folder (e.g. `user-service`, `job-service`, `ai-service`).

---

## 🧰 Useful Maven & Docker Commands

| Task | Command | Directory |
| ---- | ------- | --------- |
| Build all services | `mvn clean install` | `careerorbit-backend` |
| Skip tests build | `mvn clean install -DskipTests` | `careerorbit-backend` |
| Build Docker images with Jib | `mvn jib:dockerBuild` | `careerorbit-backend` |
| Run all containers | `docker-compose up -d` | `careerorbit-docker` |
| Stop all containers | `docker-compose down` | `careerorbit-docker` |
| Check container logs | `docker-compose logs -f [service_name]` | `careerorbit-docker` |

---

## 🧑‍💻 Author

**Rahul Nanhore**  
*Full Stack Developer*  
🌐 [LinkedIn](https://www.linkedin.com/in/rahulnanhore)  
🐙 [GitHub](https://github.com/DevRahuL-01)  

---

⭐ **If this project helped you, consider giving it a star!**
