# JDK 17 ka stable version use kar rahe hain
FROM eclipse-temurin:17-jdk-alpine

# Application folder create karna
WORKDIR /app

# Aapki build ki hui jar file ko container mein copy karna
COPY target/*.jar app.jar

# Spring Boot ka default port 8080 open karna
EXPOSE 8080

# Application start karne ki command
ENTRYPOINT ["java", "-jar", "app.jar"]
