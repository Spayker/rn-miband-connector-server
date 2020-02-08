FROM openjdk:11-jre

ADD ./target/config.jar /app/
CMD ["java", "-Xmx400m", "-jar", "/app/config.jar"]

HEALTHCHECK --interval=3s --timeout=6s --retries=3 CMD curl -f http://localhost:$CONFIG_SERVICE_DEV_PORT/actuator/health || exit 1

EXPOSE $CONFIG_SERVICE_DEV_PORT $CONFIG_SERVICE_DEV_DEBUG_PORT
