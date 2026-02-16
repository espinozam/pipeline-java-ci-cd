package eu.cifpfbmoll.pipeline_java_ci_cd;

import eu.cifpfbmoll.pipeline_java_ci_cd.repo.ActivityRepository;
import eu.cifpfbmoll.pipeline_java_ci_cd.repo.AthleteRepository;
import eu.cifpfbmoll.pipeline_java_ci_cd.repo.CoachRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class FixtureLoadingIT {

    private final AthleteRepository athleteRepository;
    private final CoachRepository coachRepository;
    private final ActivityRepository activityRepository;

    FixtureLoadingIT(
            final AthleteRepository athleteRepository,
            final CoachRepository coachRepository,
            final ActivityRepository activityRepository
    ) {
        this.athleteRepository = athleteRepository;
        this.coachRepository = coachRepository;
        this.activityRepository = activityRepository;
    }

    @Test
    void shouldLoadTenFixturesPerTable() {
        assertEquals(10, athleteRepository.count());
        assertEquals(10, coachRepository.count());
        assertEquals(10, activityRepository.count());
    }
}
