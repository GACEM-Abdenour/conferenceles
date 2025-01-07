package tp.isilB.conferenceles.dto;

public class SoumissionDTO {
    private int id;
    private String nom;
    private String description;
    private int utilisateurId;
    private int conferenceId;

    public SoumissionDTO(int id, String nom, String description, int utilisateurId, int conferenceId) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.utilisateurId = utilisateurId;
        this.conferenceId = conferenceId;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getUtilisateurId() { return utilisateurId; }
    public void setUtilisateurId(int utilisateurId) { this.utilisateurId = utilisateurId; }
    public int getConferenceId() { return conferenceId; }
    public void setConferenceId(int conferenceId) { this.conferenceId = conferenceId; }
}