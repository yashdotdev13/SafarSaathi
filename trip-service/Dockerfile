
FROM maven:3.9.8-eclipse-temurin-21 AS builder

WORKDIR /build


COPY common-module ./common-module

WORKDIR /build/common-module
RUN mvn clean install -DskipTests


WORKDIR /build

COPY trip-service ./trip-service

WORKDIR /build/trip-service
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /build/trip-service/target/*.jar app.jar

EXPOSE 9030

ENTRYPOINT ["java","-jar","app.jar","--spring.profiles.active=docker"]