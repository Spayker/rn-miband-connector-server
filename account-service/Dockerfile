FROM java:8-jre
MAINTAINER Oleksandr Kuzmin <spykerstar@gmail.com>

ADD ./target/account-service.jar /app/
CMD ["java", "-Xmx400m", "-jar", "/app/account-service.jar"]

EXPOSE 6000