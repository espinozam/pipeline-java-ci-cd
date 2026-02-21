package eu.cifpfbmoll.pipeline_java_ci_cd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import eu.cifpfbmoll.pipeline_java_ci_cd.model.ActivityType;
import eu.cifpfbmoll.pipeline_java_ci_cd.repository.ActivityRepository;
import eu.cifpfbmoll.pipeline_java_ci_cd.repository.AthleteRepository;
import eu.cifpfbmoll.pipeline_java_ci_cd.repository.CoachRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("it")
class FixturesLoadedIT {

  // Repositorios para acceder a los datos
  @Autowired private AthleteRepository athleteRepository;
  @Autowired private CoachRepository coachRepository;
  @Autowired private ActivityRepository activityRepository;

  // Verifica que se carguen 10 atletas
  @Test
  void athletes_fixtures_count_10() {
    assertEquals(10, athleteRepository.count());
  }

  // Verifica que se carguen 10 entrenadores
  @Test
  void coaches_fixtures_count_10() {
    assertEquals(10, coachRepository.count());
  }

  // Verifica que se carguen 10 actividades
  @Test
  void activities_fixtures_count_10() {
    assertEquals(10, activityRepository.count());
  }

  // Verifica que existan actividades de ambos tipos (TRAINING y COMPETITION)
  @Test
  void activities_have_both_types() {
    assertTrue(activityRepository.findByType(ActivityType.TRAINING).size() > 0);
    assertTrue(activityRepository.findByType(ActivityType.COMPETITION).size() > 0);
  }
}
