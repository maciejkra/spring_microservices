FROM maven:3.8-jdk11 as builder
COPY ./ ./
RUN mvn clean package -DskipTests


FROM openjdk:jre-alpine
EXPOSE 8080
ENV DB_HOST=db DB_PORT=3306 DB_NAME=spring_app DB_USER=app DB_PASS=pass
COPY --from=builder /target/micro-0.0.1-SNAPSHOT.jar /micro-0.0.1-SNAPSHOT.jar
CMD [ "java", "-jar", "/micro-0.0.1-SNAPSHOT.jar"]
