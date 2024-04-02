package de.starwit.batch.model;

public class Training {

    private String category;

    private String title;

    private int durationInDays;

    private String trainer;

    private Integer minParticipants;

    private Integer maxParticipants;

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

    @Override
    public String toString() {
        return "Training [category=" + category + ", title=" + title + ", durationInDays=" + durationInDays
                + ", trainer=" + trainer + ", minParticipants=" + minParticipants + ", maxParticipants="
                + maxParticipants + "]";
    }

}
