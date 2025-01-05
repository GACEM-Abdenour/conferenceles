package tp.isilB.conferenceles.dtos;

public class SoumissionDTO {
    private int id;
    private String nom;
    private String description;
    private int conferenceId; // ID of the conference this submission belongs to

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getConferenceId() { return conferenceId; }
    public void setConferenceId(int conferenceId) { this.conferenceId = conferenceId; }
}