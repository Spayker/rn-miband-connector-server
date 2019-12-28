FROM openjdk:11-jre

ADD ./target/device-service.jar /app/
CMD ["java", "-Xmx400m", "-jar", "/app/device-service.jar"]

EXPOSE $DEVICE_SERVICE_DEV_PORT $DEVICE_SERVICE_DEV_DEBUG_PORT