package tp.isilB.conferenceles.dto;

import java.util.List;

public class SoumissionWithDetailsDTO {
    private int id;
    private String nom;
    private String description;
    private int conferenceId;
    private int creatorId;
    private List<EvaluationForSoumissionDTO> evaluations;


    public SoumissionWithDetailsDTO(int id, String nom, String description, int conferenceId, int creatorId, List<EvaluationForSoumissionDTO> evaluations) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.conferenceId = conferenceId;
        this.creatorId = creatorId;
        this.evaluations = evaluations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(int conferenceId) {
        this.conferenceId = conferenceId;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public List<EvaluationForSoumissionDTO> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(List<EvaluationForSoumissionDTO> evaluations) {
        this.evaluations = evaluations;
    }
}