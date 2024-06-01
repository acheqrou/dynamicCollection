# Use the official OpenJDK 21 image as the base image
FROM eclipse-temurin:21-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot application JAR file into the container
# Assuming your JAR file is named `app.jar`
COPY target/app.jar /app/app.jar

# Expose the port that your Spring Boot application will run on
# Change this if your application runs on a different port
EXPOSE 8080

# Define the command to run your application
CMD ["java", "-jar", "app.jar"]
