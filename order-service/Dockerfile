# Use OpenJDK as the base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy built jar file into the container
COPY target/order-service-*.jar app.jar

# Expose port (match server.port in application.properties)
EXPOSE 8081

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
