# Используем офиц образ OpenJDK
FROM openjdk:17-jdk-slim

# Указываем рабочую директорию внутри контейнера
WORKDIR /app

COPY target/spring-boot-docker.jar spring-api.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "spring-api.jar"]