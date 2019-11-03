FROM java:8-jre
MAINTAINER Oleksandr Kuzmin <spykerstar@gmail.com>

ADD ./target/registry.jar /app/
CMD ["java", "-Xmx400m", "-jar", "/app/registry.jar"]

EXPOSE 8761