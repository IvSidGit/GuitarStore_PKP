## Состав

- `guitar-catalog-service` – сохранённое состояние работы 2.
- `recommendation-service` – новое API на порту 8083 с проверкой JWT и ролей.
- `infra/keycloak/course-realm.json` – минимальный realm с тремя машинными клиентами.

## Запуск основного сценария

Нужны JDK 21+, PowerShell 7 и Docker Desktop с Linux-контейнерами. Остановите Java-процессы прошлого снимка и выполните в его каталоге `docker compose down`. Затем перейдите в каталог этого снимка:

```powershell
docker compose up -d keycloak
docker compose logs keycloak
Invoke-RestMethod http://localhost:8085/realms/course/.well-known/openid-configuration |
  Select-Object issuer,token_endpoint,jwks_uri
.\mvnw.cmd -pl recommendation-service spring-boot:run
```

## Изменение realm и остановка

При обычном повторном старте существующий realm не перезаписывается. После изменения JSON:

```powershell
docker compose up -d --force-recreate keycloak
```
