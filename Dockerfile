# Build Stage
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# Production Stage
FROM eclipse-temurin:21-jre

RUN groupadd -r appuser && useradd -r -g appuser appuser

WORKDIR /app
COPY --from=build --chown=appuser:appuser /app/target/*.jar app.jar

USER appuser
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]