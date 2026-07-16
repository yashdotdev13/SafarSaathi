FROM maven:3.9.8-eclipse-temurin-21 AS builder

WORKDIR /build

# Copy discovery-server project
COPY discovery-server ./discovery-server

WORKDIR /build/discovery-server

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /build/discovery-server/target/*.jar app.jar

EXPOSE 8761

ENTRYPOINT ["java","-jar","app.jar","--spring.profiles.active=docker"]