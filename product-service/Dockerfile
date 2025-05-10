# Use lightweight Java base image
FROM openjdk:17-alpine

# Set the working directory
WORKDIR /app

# Copy the built jar file
COPY target/product-service-*.jar app.jar

# Expose service port
EXPOSE 8080

# Set the entrypoint
ENTRYPOINT ["java", "-jar", "app.jar"]
