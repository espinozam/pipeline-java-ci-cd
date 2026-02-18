package eu.cifpfbmoll.pipeline_java_ci_cd.controller;

import eu.cifpfbmoll.pipeline_java_ci_cd.model.Activity;
import eu.cifpfbmoll.pipeline_java_ci_cd.model.ActivityType;
import eu.cifpfbmoll.pipeline_java_ci_cd.service.ActivityService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

  private final ActivityService service;

  public ActivityController(ActivityService service) {
    this.service = service;
  }

  @GetMapping
  public List<Activity> list(@RequestParam(required = false) ActivityType type) {
    if (type == null) {
      return service.list();
    }
    return service.listByType(type);
  }

  @GetMapping("/{id}")
  public Activity get(@PathVariable Long id) {
    return service.get(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Activity create(@Valid @RequestBody Activity activity) {
    return service.create(activity);
  }
}
