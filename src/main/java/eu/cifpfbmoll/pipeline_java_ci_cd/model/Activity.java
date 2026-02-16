package eu.cifpfbmoll.pipeline_java_ci_cd.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "activities")
public class Activity {

    // enumerado para tipo de actividad
    public enum ActivityType {
        TRAINING,
        COMPETITION
    }

    // atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ActivityType type;

    @NotNull
    @Column(nullable = false)
    private Integer durationMinutes;

    // constructores
    protected Activity() {}

    public Activity(final String name, final ActivityType type, final Integer durationMinutes) {
        this.name = name;
        this.type = type;
        this.durationMinutes = durationMinutes;
    }

    // getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public ActivityType getType() { return type; }
    public void setType(ActivityType type) { this.type = type; }

    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }
}
