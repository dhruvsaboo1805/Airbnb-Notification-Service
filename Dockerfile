# Stage 1 — Build
FROM gradle:8-jdk21 AS builder

WORKDIR /app

COPY . .

RUN gradle build -x test

# Stage 2 — Runtime
FROM eclipse-temurin:21-alpine

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]