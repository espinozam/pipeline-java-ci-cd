# pipeline-java-ci-cd

Aplicación headless (API REST) desarrollada con Spring Boot + Maven + JPA, desplegada como WAR** en Apache Tomcat y PostgreSQL como base de datos.

La práctica está enfocada en:

-   Configuración de `docker-compose.yml` para entorno de desarrollo.
-   Creación de datos de prueba (fixtures) (\~10 registros por tabla).
-   Implementación de tests unitarios e integración con JUnit.
-   Configuración de pipeline de Continuous Integration con Github Actions:
  -   Verificar estilo de código (Checkstyle)
  -   Ejecutar tests unitarios
  -   Ejecutar tests de integracion (aceptacion)
  -   Construir imagen Docker

Archivos importantes:

-   src/main/resources/data.sql: Fixtures (10 registros por tabla)
-   docker-compose.yml: Entorno de desarrollo (PostgreSQL + Tomcat)
-   .github/workflows/ci.yml: Pipeline de integración continua (CI)

## Requisitos

-   Docker + Docker Compose
-   Java 21
-   Maven 3

## Cómo ejecutar la aplicación (desarrollo)

### 1) Levantar servicios (PostgreSQL + Tomcat)

docker compose up -d

### 2) Compilar el WAR

mvn clean package

La aplicación estará disponible en:

http://localhost:8080

## Endpoints disponibles

-   GET http://localhost:8080/api/athletes
-   GET http://localhost:8080/api/coaches
-   GET http://localhost:8080/api/activities

## Datos de prueba (Fixtures)

Los datos de desarrollo están definidos en:

src/main/resources/data.sql

Se cargan automáticamente gracias a la configuración:

```
spring.sql.init.mode=always
spring.jpa.defer-datasource-initialization=true
```

## Probar la API con curl

Listar registros:

curl http://localhost:8080/api/athletes
curl http://localhost:8080/api/coaches
curl http://localhost:8080/api/activities

```bash
curl -X POST http://localhost:8080/api/athletes \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Ana","lastName":"Garcia","email":"ana_new@mail.com"}'
```

Crear un atleta (Windows CMD):

```cmd
C:\Users\edwin>curl -X POST http://localhost:8080/api/athletes ^
  -H "Content-Type: application/json" ^
  -d "{\"firstName\":\"Ana\",\"lastName\":\"Garcia\",\"email\":\"ana_new@mail.com\"}"
```

## Tests

Los test se encuentran en src/test/java/...

### Tests unitarios

mvn test

Incluyen:
- *ServiceTest
- *ValidationTest


### Tests de integración

mvn verify

Incluyen:
- FixturesLoadedIT
- \*ApiIT

## Descripción de los tests

Tests unitarios

- *ServiceTest: comprobación de lógica mínima (list/get/create) con Mockito.
- *ValidationTest: validación Bean Validation de las entidades.

Tests de integración

- FixturesLoadedIT: verifica que existen 10 registros por tabla.
- *ApiIT: verifica endpoints REST con TestRestTemplate.

## Pipeline de Integración Continua (CI)

Archivo: `.github/workflows/ci.yml`

La pipeline ejecuta automáticamente:

1.  Configura Java 21.
2.  Ejecuta Checkstyle.
3.  Ejecuta tests unitarios.
4.  Ejecuta tests de integración.
5.  Construye la imagen Docker.


