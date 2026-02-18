package eu.cifpfbmoll.pipeline_java_ci_cd.controller;

import eu.cifpfbmoll.pipeline_java_ci_cd.model.Coach;
import eu.cifpfbmoll.pipeline_java_ci_cd.service.CoachService;
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
@RequestMapping("/api/coaches")
public class CoachController {

  private final CoachService service;

  public CoachController(CoachService service) {
    this.service = service;
  }

  @GetMapping
  public List<Coach> list() {
    return service.list();
  }

  @GetMapping("/{id}")
  public Coach get(@PathVariable Long id) {
    return service.get(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Coach create(@Valid @RequestBody Coach coach) {
    return service.create(coach);
  }
}
