## Запуск

Остановите Java-процессы и Compose предыдущего снимка. В каталоге этого снимка выполните:

```powershell
docker compose up -d postgres redis keycloak
docker compose ps
Invoke-RestMethod http://localhost:8085/realms/course/.well-known/openid-configuration |
  Select-Object issuer
.\mvnw.cmd test
```

Последнюю команду `Invoke-RestMethod` повторяйте, пока Keycloak не ответит: gateway требует OIDC metadata уже при запуске. В двух отдельных терминалах из того же каталога запустите:

```powershell
.\mvnw.cmd -pl guitar-catalog-service spring-boot:run
```

```powershell
.\mvnw.cmd -pl gateway-service spring-boot:run
```

Откройте `http://localhost:8088` и войдите как `editor/editor` либо `student/student`. Роль `reader` позволяет читать каталог, роль `editor` – также добавлять бренды и гитары. В браузерных DevTools токена в Local Storage быть не должно. `/api/brands` и `/api/guitars` через gateway используют Token Relay; прямой запрос к `http://localhost:8080/api/guitars` без `Authorization` получает `401`. POST через gateway требует CSRF header, полученный из `/bff/csrf`.

Если порт 8088 уже занят, можно запустить gateway с `$env:SERVER_PORT='18088'` и открыть `http://localhost:18088`: оба точных redirect URI зарегистрированы в учебном realm. Если Docker исчерпал автоматический пул подсетей, только после проверки пересечений с локальными/VPN сетями примените `docker compose -f compose.yaml -f compose.local-network.yaml up -d postgres redis keycloak`. При дальнейшем управлении этим Compose-проектом повторяйте оба `-f`.

Изменения `infra/keycloak/course-realm.json` подхватываются при пересоздании контейнера Keycloak: `docker compose up -d --force-recreate keycloak`. Не используйте `down -v`, если хотите сохранить данные PostgreSQL и Redis. Для обычной остановки выполните `docker compose down`.
