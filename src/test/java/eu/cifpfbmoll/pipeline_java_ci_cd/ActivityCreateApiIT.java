package eu.cifpfbmoll.pipeline_java_ci_cd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import eu.cifpfbmoll.pipeline_java_ci_cd.model.Activity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ActivityCreateApiIT {

    @Autowired
    private TestRestTemplate rest;

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
