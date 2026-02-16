package eu.cifpfbmoll.pipeline_java_ci_cd.repo;

import eu.cifpfbmoll.pipeline_java_ci_cd.model.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {
}
