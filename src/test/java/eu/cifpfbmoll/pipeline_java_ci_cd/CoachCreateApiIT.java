package eu.cifpfbmoll.pipeline_java_ci_cd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import eu.cifpfbmoll.pipeline_java_ci_cd.model.Coach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class CoachCreateApiIT {

    @Autowired
    private TestRestTemplate rest;

    // Prueba que POST /api/coaches crea un nuevo entrenador
    @Test
    void post_valid_returns_201_and_body() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); // Indica que enviamos JSON

        String json = "{\"firstName\":\"New\",\"lastName\":\"Coach\",\"email\":\"new_coach@mail.com\"}";

        // Envía petición POST con el JSON
        ResponseEntity<Coach> res = rest.exchange(
                "/api/coaches",
                HttpMethod.POST,
                new HttpEntity<>(json, headers),
                Coach.class);

        assertEquals(HttpStatus.CREATED, res.getStatusCode());
        assertNotNull(res.getBody());
        assertNotNull(res.getBody().getId());
    }
}
