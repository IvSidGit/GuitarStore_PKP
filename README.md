## Запуск

Нужны JDK 21+, PowerShell 7 и Docker Desktop с Linux-контейнерами. Остановите приложения и Compose прежнего снимка. В корне данного снимка выполните:

~~~powershell
docker compose up -d
docker compose ps
Invoke-RestMethod http://localhost:8085/realms/course/.well-known/openid-configuration |
  Select-Object issuer
Invoke-WebRequest http://localhost:3200/ready
.\mvnw.cmd -DskipTests install
~~~

При первом запуске дождитесь готовности Keycloak и Tempo. В трёх терминалах из корня снимка запустите:

~~~powershell
.\mvnw.cmd -pl recommendation-service spring-boot:run
~~~

~~~powershell
.\mvnw.cmd -pl guitar-catalog-service spring-boot:run
~~~

~~~powershell
.\mvnw.cmd -pl gateway-service spring-boot:run
~~~

## Проверка

~~~powershell
.\mvnw.cmd test
.\scripts\verify-tracing.ps1 | Format-List
~~~

Файлы логов находятся в `runtime-logs` корня снимка при запуске через Maven Wrapper.
