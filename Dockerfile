FROM ubuntu:22.04

# Install necessary tools and OpenJDK
RUN apt-get update && \
    apt-get install -y \
    wget \
    curl \
    unzip \
    ca-certificates \
    openjdk-8-jdk \
    maven

# Set working directory
WORKDIR /app

# Copy your project files
COPY . .

# Copy the swift-cache JAR to the Docker context
COPY libs/SwiftCache.jar /app/libs/SwiftCache.jar

# Install the swift-cache JAR in the Docker container
RUN mvn install:install-file -Dfile=/app/libs/SwiftCache.jar -DgroupId=org.swiftcache -DartifactId=SwiftCache -Dversion=1.0.0 -Dpackaging=jar

# Install dependencies
RUN mvn clean install

# Copy the JAR file to the image
COPY out/artifacts/swift_cache_demo_jar/swift-cache-demo.jar /app/bookstore.jar

# Expose the application port (default Spring Boot port is 8080)
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "/app/bookstore.jar"]
