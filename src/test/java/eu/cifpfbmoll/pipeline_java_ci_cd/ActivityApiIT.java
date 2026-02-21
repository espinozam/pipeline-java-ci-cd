package eu.cifpfbmoll.pipeline_java_ci_cd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import eu.cifpfbmoll.pipeline_java_ci_cd.model.Activity;
import eu.cifpfbmoll.pipeline_java_ci_cd.model.ActivityType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("it")
class ActivityApiIT {

  @Autowired private TestRestTemplate rest;

  // Prueba que GET /api/activities devuelve 10 actividades
  @Test
  void get_all_returns_10() {
    ResponseEntity<Activity[]> res = rest.getForEntity("/api/activities", Activity[].class);
    assertEquals(HttpStatus.OK, res.getStatusCode()); // Verifica código 200
    assertNotNull(res.getBody()); // Verifica que hay respuesta
    assertEquals(10, res.getBody().length); // Verifica que hay 10 elementos
  }

  // Prueba que GET /api/activities/1 devuelve una actividad específica
  @Test
  void get_by_id_returns_entity() {
    ResponseEntity<Activity> res = rest.getForEntity("/api/activities/1", Activity.class);
    assertEquals(HttpStatus.OK, res.getStatusCode()); // Verifica código 200
    assertNotNull(res.getBody()); // Verifica que hay respuesta
    assertNotNull(res.getBody().getName()); // Verifica que tiene nombre
  }

  // Prueba que el filtro por tipo devuelve solo actividades de ese tipo
  @Test
  void filter_by_type_returns_subset() {
    ResponseEntity<Activity[]> res = rest.getForEntity("/api/activities?type=" + ActivityType.TRAINING,
        Activity[].class);
    assertEquals(HttpStatus.OK, res.getStatusCode()); // Verifica código 200
    assertNotNull(res.getBody()); // Verifica que hay respuesta
    // data.sql contiene ambos tipos
    assertNotNull(res.getBody()[0].getType()); // Verifica que tiene tipo
  }

  // Prueba que POST /api/activities crea una nueva actividad
  @Test
  void post_valid_returns_201_and_body() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON); // Indica que enviamos JSON

    String json = "{\"name\":\"New Activity\",\"type\":\"TRAINING\",\"durationMinutes\":25}";

    // Envía petición POST con el JSON
    ResponseEntity<Activity> res = rest.exchange(
        "/api/activities",
        HttpMethod.POST,
        new HttpEntity<>(json, headers),
        Activity.class);

    assertEquals(HttpStatus.CREATED, res.getStatusCode()); // Verifica código 201
    assertNotNull(res.getBody()); // Verifica que hay respuesta
    assertNotNull(res.getBody().getId()); // Verifica que se asignó un ID
  }
}
