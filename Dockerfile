FROM openjdk:17-oracle
MAINTAINER "messenger"
COPY build/libs/messenger-0.0.1-SNAPSHOT.jar messenger_docker.jar
ENTRYPOINT ["java", "-jar", "messenger_docker.jar"]