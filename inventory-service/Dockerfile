# Use a lightweight OpenJDK image
FROM openjdk:17-alpine

# Set working directory inside the container
WORKDIR /app

# Copy the built JAR from target to app folder
COPY target/inventory-service-*.jar app.jar

# Expose the application port
EXPOSE 8082

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
