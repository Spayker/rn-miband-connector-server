FROM openjdk:11-jre

ADD ./target/gateway-point.jar /app/
CMD ["java", "-Xmx400m", "-jar", "/app/gateway-point.jar"]

EXPOSE $GATEWAY_SERVICE_DEV_PORT $GATEWAY_SERVICE_DEV_DEBUG_PORT