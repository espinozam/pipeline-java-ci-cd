package eu.cifpfbmoll.pipeline_java_ci_cd.repository;

import eu.cifpfbmoll.pipeline_java_ci_cd.model.Activity;
import eu.cifpfbmoll.pipeline_java_ci_cd.model.ActivityType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
  List<Activity> findByType(ActivityType type);
}
