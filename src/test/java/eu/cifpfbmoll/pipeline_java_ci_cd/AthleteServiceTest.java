package eu.cifpfbmoll.pipeline_java_ci_cd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import eu.cifpfbmoll.pipeline_java_ci_cd.exception.NotFoundException;
import eu.cifpfbmoll.pipeline_java_ci_cd.model.Athlete;
import eu.cifpfbmoll.pipeline_java_ci_cd.repository.AthleteRepository;
import eu.cifpfbmoll.pipeline_java_ci_cd.service.AthleteService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class AthleteServiceTest {

  private AthleteRepository repository;
  private AthleteService service;

  // Configura los mocks antes de cada test
  @BeforeEach
  void setUp() {
    repository = Mockito.mock(AthleteRepository.class); // Crea un mock del repositorio
    service = new AthleteService(repository); // Inyecta el mock en el servicio
  }

  // Prueba que list() devuelve todos los atletas
  @Test
  void list_returns_all() {
    when(repository.findAll()).thenReturn(List.of(new Athlete(1L, "A", "B", "a@b.com")));
    assertEquals(1, service.list().size());
  }

  // Prueba que get() devuelve un atleta existente
  @Test
  void get_existing_returns_entity() {
    when(repository.findById(1L)).thenReturn(Optional.of(new Athlete(1L, "A", "B", "a@b.com")));
    assertEquals("A", service.get(1L).getFirstName());
  }

  // Prueba que buscar un atleta inexistente lanza NotFoundException
  @Test
  void get_missing_throws_not_found() {
    when(repository.findById(99L)).thenReturn(Optional.empty()); // Simula que no existe
    assertThrows(NotFoundException.class, () -> service.get(99L)); // Verifica la excepción
  }

  // Prueba que create() establece el ID a null y guarda el atleta
  @Test
  void create_sets_null_id_and_saves() {
    Athlete input = new Athlete(123L, "Ana", "Garcia", "ana@mail.com");
    when(repository.save(any(Athlete.class))).thenAnswer(inv -> {
      Athlete a = inv.getArgument(0);
      a.setId(1L); // Simula que la BD asigna un ID
      return a;
    });

    Athlete created = service.create(input);

    assertNotNull(created.getId()); // Verifica que se asignó un ID
    verify(repository).save(any(Athlete.class)); // Verifica que se llamó a save()
  }
}
