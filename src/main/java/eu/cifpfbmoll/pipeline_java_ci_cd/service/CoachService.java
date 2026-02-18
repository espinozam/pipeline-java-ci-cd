package eu.cifpfbmoll.pipeline_java_ci_cd.service;

import eu.cifpfbmoll.pipeline_java_ci_cd.exception.NotFoundException;
import eu.cifpfbmoll.pipeline_java_ci_cd.model.Coach;
import eu.cifpfbmoll.pipeline_java_ci_cd.repository.CoachRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachService {

  private final CoachRepository repository;

  public CoachService(CoachRepository repository) {
    this.repository = repository;
  }

  public List<Coach> list() {
    return repository.findAll();
  }

  public Coach get(Long id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Coach not found: " + id));
  }

  public Coach create(Coach coach) {
    return repository.save(coach);
  }
}
