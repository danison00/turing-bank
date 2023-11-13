FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

RUN apt-get install maven -y
RUN mvn clean install

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /target/turing-bank-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ "java", "-jar" ,"app.jar"]


# FROM maven:3.8.5-openjdk-17 as build
# RUN mkdir -p /usr/src/app
# WORKDIR /usr/src/app
# ADD . /usr/src/app
# RUN mvn package

# FROM eclipse-temurin:17-jdk
# RUN mkdir -p /usr/src/app
# WORKDIR /usr/src/app
# COPY --from=build /usr/src/app/target/banco-digital-descomplicado-0.0.1-SNAPSHOT.jar app.jar
# EXPOSE 8080
# CMD ["java", "-jar", "app.jar"]