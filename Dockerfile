FROM maven:3.9.9 AS build

WORKDIR /app

COPY /pom.xml .

COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar /app/app.jar

ENV JAVA_OPTS=""

ENTRYPOINT ["java", "-jar", "/app/app.jar"]