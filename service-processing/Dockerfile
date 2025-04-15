# используем базовый образ JDK 17
FROM eclipse-temurin:17-jdk-alpine

# рабочая директория внутри контейнера
WORKDIR /app

# копируем собранный jar-файл внутрь контейнера
COPY target/service-processing-0.0.1-SNAPSHOT.jar app.jar

# указываем порт, на котором работает приложение
EXPOSE 8082

# команда запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]
