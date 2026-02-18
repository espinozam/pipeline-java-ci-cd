package eu.cifpfbmoll.pipeline_java_ci_cd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import eu.cifpfbmoll.pipeline_java_ci_cd.exception.NotFoundException;
import eu.cifpfbmoll.pipeline_java_ci_cd.model.Coach;
import eu.cifpfbmoll.pipeline_java_ci_cd.repository.CoachRepository;
import eu.cifpfbmoll.pipeline_java_ci_cd.service.CoachService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CoachServiceTest {

  private CoachRepository repository;
  private CoachService service;

  // Configura los mocks antes de cada test
  @BeforeEach
  void setUp() {
    repository = Mockito.mock(CoachRepository.class); // Crea un mock del repositorio
    service = new CoachService(repository); // Inyecta el mock en el servicio
  }

  // Prueba que list() devuelve todos los entrenadores
  @Test
  void list_returns_all() {
    when(repository.findAll()).thenReturn(List.of(new Coach(1L, "A", "B", "a@b.com")));
    assertEquals(1, service.list().size()); // Verifica que devuelve 1 elemento
  }

  // Prueba que get() devuelve un entrenador existente
  @Test
  void get_existing_returns_entity() {
    when(repository.findById(1L)).thenReturn(Optional.of(new Coach(1L, "A", "B", "a@b.com")));
    assertEquals("B", service.get(1L).getLastName());
  }

  // Prueba que buscar un entrenador inexistente lanza NotFoundException
  @Test
  void get_missing_throws_not_found() {
    when(repository.findById(99L)).thenReturn(Optional.empty());
    assertThrows(NotFoundException.class, () -> service.get(99L));
  }

  // Prueba que create() establece el ID a null y guarda el entrenador
  @Test
  void create_sets_null_id_and_saves() {
    Coach input = new Coach(123L, "Laura", "Trainer", "laura@mail.com");
    when(repository.save(any(Coach.class))).thenAnswer(inv -> {
      Coach c = inv.getArgument(0);
      c.setId(1L); // Simula que la BD asigna un ID
      return c;
    });

    Coach created = service.create(input);

    assertNotNull(created.getId()); // Verifica que se asignó un ID
    verify(repository).save(any(Coach.class)); // Verifica que se llamó a save()
  }
}
