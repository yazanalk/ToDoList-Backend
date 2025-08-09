# Stage 1: Build the JAR
FROM maven:3-eclipse-temurin-17 AS build
WORKDIR /app

# Copy Maven project files
COPY pom.xml .
COPY src ./src

# Build the application without running tests
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-alpine
WORKDIR /app

# Copy JAR file from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port (Render will override with $PORT)
EXPOSE 8080

# Start the application
ENTRYPOINT ["java", "-jar", "app.jar"]
