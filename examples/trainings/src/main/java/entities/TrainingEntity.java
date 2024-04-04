package entities;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TrainingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  
    private String category;

    private String title;

    @JsonbProperty("duration-in-days")
    @Column(name = "duration_in_days", nullable = false)
    private int durationInDays;

    private String trainer;

    @JsonbProperty("min-participants")
    @Column(name = "min_participants")
    private Integer minParticipants;
    
    @JsonbProperty("max-participants")
    @Column(name = "max_participants")
    private Integer maxParticipants;

    public TrainingEntity() {
        
    };

    public TrainingEntity(String category, String title, int durationInDays, String trainer, Integer minParticipants,
    Integer maxParticipants) {
        this.category = category;
        this.title = title;
        this.durationInDays = durationInDays;
        this.trainer = trainer;
        this.minParticipants = minParticipants;
        this.maxParticipants = maxParticipants;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public Integer getMinParticipants() {
        return minParticipants;
    }

    public void setMinParticipants(Integer minParticipants) {
        this.minParticipants = minParticipants;
    }

    public Integer getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(Integer maxParticipants) {
        this.maxParticipants = maxParticipants;
    }
}
