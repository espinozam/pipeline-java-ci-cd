package eu.cifpfbmoll.pipeline_java_ci_cd.repository;

import eu.cifpfbmoll.pipeline_java_ci_cd.model.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoachRepository extends JpaRepository<Coach, Long> {
  Optional<Coach> findByEmail(String email);
}
