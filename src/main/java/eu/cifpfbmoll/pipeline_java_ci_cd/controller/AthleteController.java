package eu.cifpfbmoll.pipeline_java_ci_cd.controller;

import eu.cifpfbmoll.pipeline_java_ci_cd.model.Athlete;
import eu.cifpfbmoll.pipeline_java_ci_cd.repo.AthleteRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/athletes")
public class AthleteController {

    private final AthleteRepository athleteRepository;

    public AthleteController(final AthleteRepository athleteRepository) {
        this.athleteRepository = athleteRepository;
    }

    @GetMapping
    public List<Athlete> list() {
        return athleteRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Athlete create(@Valid @RequestBody final Athlete athlete) {
        return athleteRepository.save(athlete);
    }
}
