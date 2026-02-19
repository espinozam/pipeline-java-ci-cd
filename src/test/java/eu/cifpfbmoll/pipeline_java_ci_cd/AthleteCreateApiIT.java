package eu.cifpfbmoll.pipeline_java_ci_cd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import eu.cifpfbmoll.pipeline_java_ci_cd.model.Athlete;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AthleteCreateApiIT {

    @Autowired
    private TestRestTemplate rest;

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
