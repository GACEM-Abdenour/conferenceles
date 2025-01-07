package tp.isilB.conferenceles.dto;

import java.util.Set;

public class ConferenceDTO {
    private int id;
    private String titre;
    private String dateDebut;
    private String dateFin;
    private String theme;
    private String etat;
    private Set<Integer> soumissionIds; // Change to Set<Integer>
    private UtilisateurDTO utilisateur;

    public ConferenceDTO(int id, String titre, String dateDebut, String dateFin, String theme, String etat, Set<Integer> soumissionIds, UtilisateurDTO utilisateur) {
        this.id = id;
        this.titre = titre;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.theme = theme;
        this.etat = etat;
        this.soumissionIds = soumissionIds; // Updated to Set<Integer>
        this.utilisateur = utilisateur;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Set<Integer> getSoumissionIds() { // Updated to Set<Integer>
        return soumissionIds;
    }

    public void setSoumissionIds(Set<Integer> soumissionIds) { // Updated to Set<Integer>
        this.soumissionIds = soumissionIds;
    }

    public UtilisateurDTO getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(UtilisateurDTO utilisateur) {
        this.utilisateur = utilisateur;
    }
}