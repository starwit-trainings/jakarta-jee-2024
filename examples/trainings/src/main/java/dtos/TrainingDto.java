package dtos;


public class TrainingDto {
    
    private String category;

    private String title;

    private int durationInDays;

    private String trainer;

    private Integer minParticipants;

    private Integer maxParticipants;

    public TrainingDto(String category, String title, int durationInDays, String trainer, Integer minParticipants,
    Integer maxParticipants) {
        this.category = category;
        this.title = title;
        this.durationInDays = durationInDays;
        this.trainer = trainer;
        this.minParticipants = minParticipants;
        this.maxParticipants = maxParticipants;
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
