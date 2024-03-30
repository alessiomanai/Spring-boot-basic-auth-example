FROM ubuntu:latest
WORKDIR /app
RUN apt-get update && \
apt-get install -y --no-install-recommends \
        openjdk-17-jre
RUN apt-get install curl -y
ADD spring-1.0.0-SNAPSHOT.jar .
ENV TZ=Europe/Rome
EXPOSE 8080
CMD java -jar /app/spring-1.0.0-SNAPSHOT.jar
