package eu.cifpfbmoll.pipeline_java_ci_cd.service;

import eu.cifpfbmoll.pipeline_java_ci_cd.exception.NotFoundException;
import eu.cifpfbmoll.pipeline_java_ci_cd.model.Activity;
import eu.cifpfbmoll.pipeline_java_ci_cd.model.ActivityType;
import eu.cifpfbmoll.pipeline_java_ci_cd.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

  private final ActivityRepository repository;

  public ActivityService(ActivityRepository repository) {
    this.repository = repository;
  }

  public List<Activity> list() {
    return repository.findAll();
  }

  public List<Activity> listByType(ActivityType type) {
    return repository.findByType(type);
  }

  public Activity get(Long id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Activity not found: " + id));
  }

  public Activity create(Activity activity) {
    return repository.save(activity);
  }
}
