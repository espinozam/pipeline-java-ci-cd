package eu.cifpfbmoll.pipeline_java_ci_cd;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import eu.cifpfbmoll.pipeline_java_ci_cd.model.Athlete;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class AthleteValidationTest {

  private static Validator validator; // Validador de anotaciones @NotBlank, @Email, etc.

  // Configura el validador una sola vez antes de todos los tests
  @BeforeAll
  static void setupValidator() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  // Prueba que un atleta válido no tiene errores de validación
  @Test
  void valid_athlete_has_no_violations() {
    Athlete a = new Athlete(null, "Ana", "Garcia", "ana@mail.com");
    assertTrue(validator.validate(a).isEmpty());
  }

  // Prueba que nombres vacíos causan error de validación
  @Test
  void blank_names_fail() {
    Athlete a = new Athlete(null, "", "", "ana@mail.com");
    assertFalse(validator.validate(a).isEmpty());
  }

  // Prueba que un email inválido causa error de validación
  @Test
  void invalid_email_fails() {
    Athlete a = new Athlete(null, "Ana", "Garcia", "not-an-email");
    assertFalse(validator.validate(a).isEmpty());
  }
}
