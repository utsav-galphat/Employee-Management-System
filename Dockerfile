FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY build/libs/Employee-Management-System-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
