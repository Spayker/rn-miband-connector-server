FROM java:8-jre

ADD ./target/gateway-point.jar /app/
CMD ["java", "-Xmx400m", "-jar", "/app/gateway-point.jar"]

EXPOSE 4000