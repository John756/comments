
# Step 1: Build the application using Maven
FROM maven:3.8.1-openjdk-17-slim AS build
WORKDIR /app

# Copy the Maven project files to the Docker image
COPY . /app/
COPY pom.xml /app/
RUN mvn dependency:go-offline

# Copy the project files
COPY src /app/src/

# Package the application (skip tests for speed)
RUN mvn clean package -DskipTests

# Step 2: Run the application using a lightweight JDK image
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the packaged jar file from the build stage
COPY --from=build /app/target/comment-0.0.1-SNAPSHOT.jar /app/comment-app.jar

# Expose the port the app will run on
EXPOSE 8081

# Set environment variables
ENV SHELL=/bin/bash \
    JAVA_HOME=/home/j/.jdks/jbr-17.0.11 \
    SPRING_APPLICATION_NAME=Prispevky \
    SPRING_DATASOURCE_URL=jdbc:h2:mem:~/COMMENTS \
    SPRING_DATASOURCE_DRIVER_CLASS_NAME=org.h2.Driver \
    SPRING_DATASOURCE_USERNAME=na \
    SPRING_DATASOURCE_PASSWORD= \
    SERVER_PORT=8081 \
    SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.H2Dialect \
    SPRING_H2_CONSOLE_ENABLED=true \
    SPRING_H2_CONSOLE_PATH=/h2-console \
    SPRING_JPA_SHOW_SQL=true

# Command to run the application
ENTRYPOINT ["java", "-jar", "comment-app.jar"]

