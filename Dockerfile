
FROM maven:3.8.7-amazoncorretto-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn dependency:go-offline -B
RUN mvn clean package -DskipTests

FROM amazoncorretto:17.0.0-alpine

WORKDIR /app

COPY --from=build app/target/ouro-gourmet-0.0.1-SNAPSHOT.jar ./ouro-gourmet.jar 

EXPOSE 8080

CMD ["java", "-jar", "ouro-gourmet.jar"]

