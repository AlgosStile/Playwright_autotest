# FCK-AUTOTESTS

## Обзор

Этот репозиторий содержит фреймворк автотестирования, построенный с использованием **Spring Boot**, 
**Cucumber** и **Playwright**. Он предоставляет структурированный подход к написанию и выполнению 
автоматизированных тестов для веб-приложений, используя возможности разработки, основанной на поведении (BDD)
с Cucumber и возможности Playwright для автоматизации браузера.

## Особенности

- **Интеграция с Spring Boot**: Легкая интеграция с Spring Boot для простой настройки и управления.
- **BDD с Cucumber**: Использует Cucumber для написания тестовых сценариев в удобочитаемом формате.
- **Автоматизация с Playwright**: Применяет Playwright для надежной и быстрой автоматизации браузера.
- **Поддержка Docker**: Поставляется с Dockerfile и конфигурацией docker-compose для простой развертки 
- и выполнения тестов в изолированных средах.

## Начало работы

### Предварительные требования

- Java 17 или выше
- Maven 3.9.6 
- Docker (по желанию, для выполнения в контейнере)

### Установка

1. **Клонируйте репозиторий**:

   ```bash
   git clone <url-репозитория>
   cd <каталог-репозитория>
   ```

2. **Соберите проект**:

   ```bash
   mvn clean package
   ```
3. **Создаем докер-образ, например `autotests` и собираем его с помощью команды:**

`docker build -t autotests .`

4. **Запускаем контейнер с помощью команды:**

 `docker run --rm autotests:latest`

5. **Запуск тестов**

   Локально:

   ```bash
   mvn test
   ```

## Структура проекта

```
.
├── Dockerfile               # Dockerfile для сборки тестовой среды
├── docker-compose.yml       # Конфигурация Docker Compose
├── pom.xml                  # Файл конфигурации Maven
├── src
│   ├── main
│   │   └── java
│   │       └── autotest        
│   │           
│   │       └── config       # Пакет для конфигурации автотестов
│   │           ├── CucumberSpringConfiguration.java
│   │           └── PlaywrightConfig.java
│   │       
│   │       └── pages        # Пакет для объектов страниц
│   │           └── PageObject.java
│   │       
│   │       └── steps        # Пакет c автотестами
│   │           └── Tests.java
│   │       
│   └── test
│       └── java
│           └── autotest     # Определения тестов
│               ├── RunCucumberTest.java
│               └── Application.java     # Основной пакет приложения
└── src/test/resources
    └── autotest
        └── test.feature      # Файлы фич Cucumber


```

## Написание тестов

### Файлы фич Cucumber

Тестовые сценарии определяются в файлах `.feature` с использованием синтаксиса Gherkin:

```gherkin
Feature: Playwright Tests

  Scenario: Check page title
    Given I open the Playwright test page
    When I check the page title
    Then I click on the cart item

  Scenario: Check page H1
    Given I open the Playwright H1 page
    When I check for the H1 header
    Then I close the browser after checking welcome header
```

### Определения шагов

Определения шагов реализуются в классах на Java. 
Каждый шаг в файле фичи соответствует методу в классе определения шагов. Например:

```java
@Given("I open the Playwright test page")
public void iOpenThePlaywrightTestPage() {
    playwright = Playwright.create();
    browser = playwright.chromium().launch();
    page = browser.newPage();
    page.navigate("https://algosstile.github.io/vue-app/index.html");
}
```

## Конфигурация

### Конфигурация Maven

Файл `pom.xml` содержит все необходимые зависимости и плагины для выполнения тестов. 
Основные зависимости включают:

- **Cucumber**: Для тестирования в стиле BDD.
- **Spring Boot**: Для настройки приложения.
- **Playwright**: Для автоматизации браузера.
- **JUnit**: Для выполнения тестов.

### Конфигурация Docker

Dockerfile собирает приложение и выполняет тесты в контейнеризованной среде.
Файл `docker-compose.yml` определяет сервис для выполнения автотестов.
Файл `Dockerfile` определяет контейнеры и конфигурацию приложения.


### Просмотр результатов тестов

Результаты тестов будут отображаться в консоле. 
Также можно настроить инструменты отчетности, для детализированных отчетов.


### Docker (полезные команды)

Собираем образ без кэширования:

`docker build --no-cache -t autotests:1 -f ./Dockerfile .`

Запускаем в интерактивном режиме для взаимодействия с контейнером:

`docker run -it autotests`

Или так:

`docker run -it --rm autotests:1 /bin/sh`

Проверка запущенных образов:

`docker images list`

Или конкретного образа:

`docker images list | grep autotests`

Запускаем контейнер с помощью команды:

`docker run -v ${PWD}:/app -e DISPLAY=:99 autotests java -jar /app/target/fck-autotests-1.0-SNAPSHOT.jar` 

---
**UPD** - смотреть комментарии в pom.xml

