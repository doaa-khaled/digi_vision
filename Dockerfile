FROM openjdk:17-jdk-slim

WORKDIR /app

COPY . /app

RUN ./mvnw clean package

EXPOSE 8080

CMD ["java", "-jar", "target/files-1.0.jar"]