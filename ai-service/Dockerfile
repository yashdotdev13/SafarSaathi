# ===========================
# Stage 1 - Build
# ===========================
FROM maven:3.9.8-eclipse-temurin-21 AS builder

WORKDIR /build

# ---------------------------
# Build common-module
# ---------------------------
COPY common-module ./common-module

WORKDIR /build/common-module
RUN mvn clean install -DskipTests

# ---------------------------
# Build ai-service
# ---------------------------
WORKDIR /build

COPY ai-service ./ai-service

WORKDIR /build/ai-service

RUN mvn clean package -DskipTests

# ===========================
# Stage 2 - Runtime
# ===========================
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /build/ai-service/target/*.jar app.jar

EXPOSE 8086

ENTRYPOINT ["java","-jar","app.jar","--spring.profiles.active=docker"]