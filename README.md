## Запуск

```powershell
Copy-Item .env.example .env
docker compose up -d postgres
.\mvnw.cmd -pl guitar-catalog-service spring-boot:run
```

Сервис доступен по адресу `http://localhost:8080`. Основные ресурсы:

- `GET /api/brands` и `POST /api/brands`;
- `GET /api/guitars`, `GET /api/guitars/{id}` и `POST /api/guitars`.

## Проверка

```powershell
.\mvnw.cmd test
docker compose exec postgres psql -U course -d catalog -c "select * from flyway_schema_history;"
```

Для остановки инфраструктуры выполните `docker compose down`. Данные сохраняются в именованном томе; команда `docker compose down -v` удалит их и нужна только для осознанного повторения работы с чистой базой.
