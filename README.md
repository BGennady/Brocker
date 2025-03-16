# Микросервисы: Service Processing и Credit Application Check

## Общее описание
Этот проект включает два микросервиса, работающих с кредитными заявками. Микросервисы используют Kafka для обмена сообщениями и RabbitMQ для отправки результатов. Первый микросервис (`Service Processing`) получает заявки через Kafka, проверяет их и отправляет результаты в RabbitMQ. Второй микросервис (`Credit Application Check`) анализирует заявки, применяя бизнес-правила, и передает результат в RabbitMQ.

---

## Микросервис 1: **Service Processing**

Микросервис обрабатывает заявки на кредит, прослушивая Kafka топик, проверяя заявки и отправляя результаты в RabbitMQ.

### Основные классы

1. **`CreditApplicationListener`**  
   Слушает Kafka топик `credit_applications` для получения новых заявок.  
   - Использует аннотацию `@KafkaListener`.
   - Преобразует строковые сообщения в объекты `CreditApplication`.
   - Отправляет заявку на обработку в `CreditApplicationProcessor`.

2. **`CreditApplicationProcessor`**  
   Обрабатывает кредитные заявки, проверяя их на соответствие бизнес-правилам.  
   - Передает заявку на проверку в `CreditApplicationChecker`.
   - Отправляет результаты в RabbitMQ через `CreditApplicationSender`.

3. **`CreditApplicationSender`**  
   Отправляет результаты обработки заявки в RabbitMQ.  
   - Использует `RabbitTemplate` для отправки сообщений о статусе заявки.

4. **`CreditApplication`**  
   Представляет структуру кредитной заявки.  
   - Поля: `id`, `loanAmount`, `loanTerm`, `userIncome`, `currentDebtLoad`, `creditRating`, `status`.

5. **`CreditApplicationChecker`**  
   Проверяет заявку на соответствие бизнес-правилам, таким как максимальная сумма кредита в зависимости от дохода.  
   - Применяет бизнес-правила для принятия решения.

6. **`ServiceProcessingApplication`**  
   Главный класс Spring Boot приложения.  
   - Инициализирует приложение и запускает Kafka listener и RabbitMQ отправку сообщений.

---

## Микросервис 2: **Credit Application Check**

Микросервис проверяет заявки, полученные от других микросервисов, анализирует их и передает результат в RabbitMQ.

### Основные классы

1. **`CreditApplicationListener`**  
   Слушает Kafka топик `credit_applications`, десериализует сообщения и передает их в `CreditApplicationProcessor`.

2. **`CreditApplicationProcessor`**  
   Обрабатывает заявку и отправляет результат в RabbitMQ через `CreditApplicationSender`.  
   - Делегирует проверку заявки в `CreditApplicationChecker`.

3. **`CreditApplicationSender`**  
   Отправляет результаты обработки заявки в RabbitMQ, используя `RabbitTemplate`.

4. **`CreditApplicationChecker`**  
   Проверяет кредитную заявку на соответствие критериям.  
   - Применяет бизнес-правила для проверки кредитоспособности.

5. **`CreditApplication`**  
   Описание структуры кредитной заявки.  
   - Поля: `id`, `loanAmount`, `loanTerm`, `userIncome`, `currentDebtLoad`, `creditRating`, `status`.

6. **`ServiceProcessingApplication`**  
   Главный класс для запуска микросервиса, инициирует подключение к Kafka и RabbitMQ.

---

## Конфигурация Kafka и RabbitMQ

### Kafka:
- `spring.kafka.bootstrap-servers` — адрес Kafka брокера.
- `spring.kafka.consumer.group-id` — ID группы Kafka consumer.
- `spring.kafka.consumer.key-deserializer` — настройка десериализации ключа.
- `spring.kafka.consumer.value-deserializer` — настройка десериализации значения.

### RabbitMQ:
- `spring.rabbitmq.host` — адрес RabbitMQ сервера.
- `spring.rabbitmq.port` — порт RabbitMQ.
- `spring.rabbitmq.username` — имя пользователя RabbitMQ.
- `spring.rabbitmq.password` — пароль RabbitMQ.

---

## `docker-compose.yml`

Файл для запуска Docker-контейнеров, включая Kafka и RabbitMQ, для работы микросервисов.

- **Kafka**: Для передачи заявок между сервисами.
- **RabbitMQ**: Для отправки результатов обработки заявок.
