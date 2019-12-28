FROM openjdk:11-jre

ADD ./target/auth-service.jar /app/
CMD ["java", "-Xmx400m", "-jar", "/app/auth-service.jar"]

EXPOSE $AUTH_SERVICE_DEV_PORT $AUTH_SERVICE_DEV_DEBUG_PORT