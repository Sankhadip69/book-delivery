FROM openjdk:17-jdk-slim

MAINTAINER sankhadip@mandal

COPY target/bookdelivery-0.0.1-SNAPSHOT.jar bookdelivery-0.0.1-SNAPSHOT.jar

EXPOSE 1221

ENTRYPOINT ["java", "-jar", "bookdelivery-0.0.1-SNAPSHOT.jar"]