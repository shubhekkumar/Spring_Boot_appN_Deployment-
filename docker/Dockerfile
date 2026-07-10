# Stage 1
FROM eclipse-temurin:17-jdk AS builder

WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

RUN chmod +x gradlew

RUN ./gradlew clean bootJar --no-daemon

# stage 2

FROM eclipse-temurin:17-jre

WORKDIR /app

RUN useradd -m spring

USER spring 

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]

