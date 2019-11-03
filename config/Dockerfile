FROM java:8-jre
MAINTAINER Oleksandr Kuzmin <spykerstar@gmail.com>

ADD ./target/config.jar /app/
CMD ["java", "-Xmx400m", "-jar", "/app/config.jar"]

HEALTHCHECK --interval=3s --timeout=6s CMD curl -f http://localhost:8888/actuator/health || exit 1

EXPOSE 8888