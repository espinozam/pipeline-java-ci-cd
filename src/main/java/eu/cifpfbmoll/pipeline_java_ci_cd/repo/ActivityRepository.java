package eu.cifpfbmoll.pipeline_java_ci_cd.repo;

import eu.cifpfbmoll.pipeline_java_ci_cd.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
