package eu.cifpfbmoll.pipeline_java_ci_cd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import eu.cifpfbmoll.pipeline_java_ci_cd.model.Athlete;
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
class AthleteApiIT {

  @Autowired private TestRestTemplate rest;

  // Prueba que GET /api/athletes devuelve 10 atletas
  @Test
  void get_all_returns_10() {
    ResponseEntity<Athlete[]> res = rest.getForEntity("/api/athletes", Athlete[].class);
    assertEquals(HttpStatus.OK, res.getStatusCode());
    assertNotNull(res.getBody());
    assertEquals(10, res.getBody().length); // Verifica que hay 10 elementos
  }

  // Prueba que GET /api/athletes/1 devuelve un atleta específico
  @Test
  void get_by_id_returns_entity() {
    ResponseEntity<Athlete> res = rest.getForEntity("/api/athletes/1", Athlete.class);
    assertEquals(HttpStatus.OK, res.getStatusCode());
    assertNotNull(res.getBody());
    assertNotNull(res.getBody().getEmail()); // Verifica que tiene email
  }

  // Prueba que buscar un ID inexistente devuelve error 404
  @Test
  void get_missing_returns_404() {
    ResponseEntity<String> res = rest.getForEntity("/api/athletes/9999", String.class);
    assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode()); // Verifica código 404
  }

  // Prueba que POST /api/athletes crea un nuevo atleta
  @Test
  void post_valid_returns_201_and_body() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON); // Indica que enviamos JSON

    String json = "{\"firstName\":\"New\",\"lastName\":\"Athlete\",\"email\":\"new_athlete@mail.com\"}";

    // Envía petición POST con el JSON
    ResponseEntity<Athlete> res = rest.exchange(
        "/api/athletes",
        HttpMethod.POST,
        new HttpEntity<>(json, headers),
        Athlete.class);

    assertEquals(HttpStatus.CREATED, res.getStatusCode());
    assertNotNull(res.getBody());
    assertNotNull(res.getBody().getId()); // Verifica que se asignó un ID
  }
}
