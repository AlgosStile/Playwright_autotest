# Этап установки зависимостей
FROM eclipse-temurin:17.0.5_8-jdk AS base
WORKDIR /app/
# Установка необходимых библиотек для Playwright
RUN apt-get update && apt-get install -y \
    libnss3 \
    libnspr4 \
    libdbus-1-3 \
    libatk1.0-0 \
    libatk-bridge2.0-0 \
    libatspi2.0-0 \
    libx11-6 \
    libxcomposite1 \
    libxdamage1 \
    libxext6 \
    libxfixes3 \
    libxrandr2 \
    libgbm1 \
    libdrm2 \
    libxcb1 \
    libxkbcommon0 \
    libasound2 \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# Этап сборки
FROM maven:3.9.6 AS builder
WORKDIR /app/
COPY pom.xml /app/
COPY src /app/src
RUN mvn clean package -DskipTests

# Этап выполнения тестов
FROM maven:3.9.6 AS tester
WORKDIR /app/
COPY --from=builder /app/target /app/target
COPY pom.xml /app/
COPY src /app/src

# Запускаем тесты
CMD ["mvn", "test"]
