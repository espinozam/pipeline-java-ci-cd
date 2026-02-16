package eu.cifpfbmoll.pipeline_java_ci_cd.repo;

import eu.cifpfbmoll.pipeline_java_ci_cd.model.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepository extends JpaRepository<Coach, Long> {
}
