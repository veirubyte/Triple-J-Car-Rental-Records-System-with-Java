# Use Maven with Java 8 to build the application
FROM maven:3.9.6-eclipse-temurin-8 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Build the application
RUN mvn clean package -DskipTests

# Use a lightweight Java 8 runtime for the final image
FROM eclipse-temurin:8-jre-alpine
WORKDIR /app
# Copy the built jar from the build stage
COPY --from=build /app/target/*.jar app.jar
# Expose the port Spring Boot uses
EXPOSE 8080
# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
