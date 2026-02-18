package eu.cifpfbmoll.pipeline_java_ci_cd.controller;

import eu.cifpfbmoll.pipeline_java_ci_cd.model.Athlete;
import eu.cifpfbmoll.pipeline_java_ci_cd.service.AthleteService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/athletes")
public class AthleteController {

  private final AthleteService service;

  public AthleteController(AthleteService service) {
    this.service = service;
  }

  @GetMapping
  public List<Athlete> list() {
    return service.list();
  }

  @GetMapping("/{id}")
  public Athlete get(@PathVariable Long id) {
    return service.get(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Athlete create(@Valid @RequestBody Athlete athlete) {
    return service.create(athlete);
  }
}
