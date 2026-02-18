package eu.cifpfbmoll.pipeline_java_ci_cd.repository;

import eu.cifpfbmoll.pipeline_java_ci_cd.model.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {
  Optional<Athlete> findByEmail(String email);
}
