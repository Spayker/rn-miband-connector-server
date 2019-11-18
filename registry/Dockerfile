FROM java:8-jre

ADD ./target/registry.jar /app/
CMD ["java", "-Xmx400m", "-jar", "/app/registry.jar"]

EXPOSE $REGISTRY_SERVICE_DEV_PORT