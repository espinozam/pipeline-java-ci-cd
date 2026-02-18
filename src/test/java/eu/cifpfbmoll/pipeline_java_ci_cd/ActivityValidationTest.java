package eu.cifpfbmoll.pipeline_java_ci_cd;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import eu.cifpfbmoll.pipeline_java_ci_cd.model.Activity;
import eu.cifpfbmoll.pipeline_java_ci_cd.model.ActivityType;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ActivityValidationTest {

  private static Validator validator; // Validador de anotaciones @NotBlank, @Positive, etc.

  // Configura el validador una sola vez antes de todos los tests
  @BeforeAll
  static void setupValidator() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  // Prueba que una actividad válida no tiene errores de validación
  @Test
  void valid_activity_has_no_violations() {
    Activity a = new Activity(null, "Training", ActivityType.TRAINING, 30);
    assertTrue(validator.validate(a).isEmpty());
  }

  // Prueba que un nombre vacío causa error de validación
  @Test
  void blank_name_fails() {
    Activity a = new Activity(null, "", ActivityType.TRAINING, 30);
    assertFalse(validator.validate(a).isEmpty());
  }

  // Prueba que la duración debe ser un número positivo
  @Test
  void duration_must_be_positive() {
    Activity a = new Activity(null, "Training", ActivityType.TRAINING, 0);
    assertFalse(validator.validate(a).isEmpty());
  }
}
