package eu.cifpfbmoll.pipeline_java_ci_cd;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import eu.cifpfbmoll.pipeline_java_ci_cd.model.Coach;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CoachValidationTest {

  private static Validator validator;

  // Configura el validador una sola vez antes de todos los tests
  @BeforeAll
  static void setupValidator() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  // Prueba que un entrenador válido no tiene errores de validación
  @Test
  void valid_coach_has_no_violations() {
    Coach c = new Coach(null, "Carlos", "Coach", "carlos@mail.com");
    assertTrue(validator.validate(c).isEmpty());
  }

  // Prueba que campos vacíos causan error de validación
  @Test
  void blank_fields_fail() {
    Coach c = new Coach(null, "", "", "");
    assertFalse(validator.validate(c).isEmpty());
  }

  // Prueba que un email inválido causa error de validación
  @Test
  void invalid_email_fails() {
    Coach c = new Coach(null, "Carlos", "Coach", "bad");
    assertFalse(validator.validate(c).isEmpty());
  }
}
