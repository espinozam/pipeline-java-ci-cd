package eu.cifpfbmoll.pipeline_java_ci_cd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import eu.cifpfbmoll.pipeline_java_ci_cd.exception.NotFoundException;
import eu.cifpfbmoll.pipeline_java_ci_cd.model.Activity;
import eu.cifpfbmoll.pipeline_java_ci_cd.model.ActivityType;
import eu.cifpfbmoll.pipeline_java_ci_cd.repository.ActivityRepository;
import eu.cifpfbmoll.pipeline_java_ci_cd.service.ActivityService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ActivityServiceTest {

  private ActivityRepository repository; // Mock del repositorio
  private ActivityService service; // Servicio a probar

  // Configura los mocks antes de cada test
  @BeforeEach
  void setUp() {
    repository = Mockito.mock(ActivityRepository.class); // Crea un mock del repositorio
    service = new ActivityService(repository); // Inyecta el mock en el servicio
  }

  // Prueba que list() devuelve todas las actividades
  @Test
  void list_returns_all() {
    when(repository.findAll()).thenReturn(List.of(new Activity(1L, "T", ActivityType.TRAINING, 60)));
    assertEquals(1, service.list().size());
  }

  // Prueba que listByType() filtra por tipo de actividad
  @Test
  void listByType_filters() {
    when(repository.findByType(ActivityType.TRAINING))
        .thenReturn(List.of(new Activity(1L, "T", ActivityType.TRAINING, 60)));
    assertEquals(1, service.listByType(ActivityType.TRAINING).size());
  }

  // Prueba que buscar una actividad inexistente lanza NotFoundException
  @Test
  void get_missing_throws_not_found() {
    when(repository.findById(99L)).thenReturn(Optional.empty());
    assertThrows(NotFoundException.class, () -> service.get(99L));
  }

  // Prueba que create() establece el ID a null y guarda la actividad
  @Test
  void create_sets_null_id_and_saves() {
    Activity input = new Activity(123L, "X", ActivityType.COMPETITION, 90);
    when(repository.save(any(Activity.class))).thenAnswer(inv -> {
      Activity a = inv.getArgument(0);
      a.setId(1L);
      return a;
    });

    Activity created = service.create(input);

    assertNotNull(created.getId()); // Verifica que se asignó un ID
    verify(repository).save(any(Activity.class)); // Verifica que se llamó a save()
  }
}
