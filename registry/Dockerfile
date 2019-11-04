FROM java:8-jre

ADD ./target/registry.jar /app/
CMD ["java", "-Xmx400m", "-jar", "/app/registry.jar"]

EXPOSE 8761