
FROM maven:3.9.8-eclipse-temurin-21 AS builder

WORKDIR /build

COPY common-module ./common-module

WORKDIR /build/common-module
RUN mvn clean install -DskipTests


WORKDIR /build

COPY api-gateway ./api-gateway

WORKDIR /build/api-gateway

RUN mvn clean package -DskipTests


FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /build/api-gateway/target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","app.jar","--spring.profiles.active=docker"]