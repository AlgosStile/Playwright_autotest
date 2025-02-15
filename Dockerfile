# Используем образ Maven с Java 17
FROM maven:3.9.6-eclipse-temurin-17 AS base
WORKDIR /app/

# Устанавливаем Playwright и его зависимости
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

# Копируем исходный код
COPY pom.xml /app/
COPY src /app/src

# Запускаем тесты
CMD ["mvn", "test"]