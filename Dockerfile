FROM maven:3.9.3 AS build
WORKDIR /app
ENV CONTAINER_PORT=${CONTAINER_PORT}
COPY pom.xml /app
RUN mvn dependency:resolve
COPY . /app
RUN mvn clean
RUN mvn package -DskipTests -X

FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /app/target/*.jar bank-0.0.1-SNAPSHOT.jar
EXPOSE ${CONTAINER_PORT}
CMD ["java","-jar","-Dspring.profiles.active=prod","bank-0.0.1-SNAPSHOT.jar"]
