# Use Eclipse Temurin JDK 17 (more reliable)
FROM eclipse-temurin:23-jre-jammy

WORKDIR /app

COPY target/logistics-app-1.0.0.jar app.jar

RUN groupadd -r spring && useradd -r -g spring spring
USER spring

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]