FROM openjdk:11-jre-slim

WORKDIR /app

# Копируем JAR файл
COPY target/*.jar app.jar

# Создаем не-root пользователя для безопасности
RUN groupadd -r javauser && useradd -r -g javauser javauser && \
    chown -R javauser:javauser /app
USER javauser

EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]