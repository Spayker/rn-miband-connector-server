FROM java:8-jre
MAINTAINER Oleksandr Kuzmin <spykerstar@gmail.com>

ADD ./target/gateway-point.jar /app/
CMD ["java", "-Xmx400m", "-jar", "/app/gateway-point.jar"]

EXPOSE 4000