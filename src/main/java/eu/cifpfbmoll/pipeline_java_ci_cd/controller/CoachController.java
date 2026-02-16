package eu.cifpfbmoll.pipeline_java_ci_cd.controller;

import eu.cifpfbmoll.pipeline_java_ci_cd.model.Coach;
import eu.cifpfbmoll.pipeline_java_ci_cd.repo.CoachRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coaches")
public class CoachController {

    private final CoachRepository coachRepository;

    public CoachController(final CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    @GetMapping
    public List<Coach> list() {
        return coachRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Coach create(@Valid @RequestBody final Coach coach) {
        return coachRepository.save(coach);
    }
}
