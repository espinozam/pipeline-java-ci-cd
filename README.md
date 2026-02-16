# pipeline-java-ci-cd

**Levantar entorno y cargar fixtures**

```bash
mvn clean package -DskipTests
docker compose up -d
```

**Resetear tablas (evitar duplicados de email)**

```bash
docker exec -it pipeline_db psql -U pipeuser -d pipedb -c "TRUNCATE athletes, coaches, activities RESTART IDENTITY;"
docker compose restart tomcat
```

**Comprobar fixtures**

```bash
curl http://localhost:8080/api/athletes
curl http://localhost:8080/api/coaches
curl http://localhost:8080/api/activities
```

### 2) Configuración de `data.sql`

En `application.properties` comprobar estas dos:

```properties
spring.sql.init.mode=always
spring.jpa.defer-datasource-initialization=true
```

