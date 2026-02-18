package eu.cifpfbmoll.pipeline_java_ci_cd.service;

import eu.cifpfbmoll.pipeline_java_ci_cd.exception.NotFoundException;
import eu.cifpfbmoll.pipeline_java_ci_cd.model.Athlete;
import eu.cifpfbmoll.pipeline_java_ci_cd.repository.AthleteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AthleteService {

  private final AthleteRepository repository;

  public AthleteService(AthleteRepository repository) {
    this.repository = repository;
  }

  public List<Athlete> list() {
    return repository.findAll();
  }

  public Athlete get(Long id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Athlete not found: " + id));
  }

  public Athlete create(Athlete athlete) {
    return repository.save(athlete);
  }
}
