FROM java:8-jre

ADD ./target/config.jar /app/
CMD ["java", "-Xmx400m", "-jar", "/app/config.jar"]

HEALTHCHECK --interval=3s --timeout=6s CMD curl -f http://localhost:$CONFIG_SERVICE_DEV_PORT/actuator/health || exit 1

EXPOSE $CONFIG_SERVICE_DEV_PORT