package eu.cifpfbmoll.pipeline_java_ci_cd.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "activities")
public class Activity {

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

  @Min(1)
  @Column(nullable = false)
  private int durationMinutes;

  // constructores
  public Activity() {}

  public Activity(final Long id, final String name, final ActivityType type, final Integer durationMinutes) {
    this.id = id;
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

