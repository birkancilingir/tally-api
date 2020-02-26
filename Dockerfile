FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} tally-api-app.jar
ENTRYPOINT ["java","-jar","/tally-api-app.jar"]