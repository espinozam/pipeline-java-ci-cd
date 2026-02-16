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
}
