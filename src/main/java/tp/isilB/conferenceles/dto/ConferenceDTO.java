package tp.isilB.conferenceles.dto;

import tp.isilB.conferenceles.entities.Soumission;

import java.util.Set;

public class ConferenceDTO {
    private int id;
    private String titre;
    private String dateDebut;
    private String dateFin;
    private String theme;
    private String etat;
    private Set<Soumission> soumissions;
    private UtilisateurDTO utilisateur;

    public ConferenceDTO(int id, String titre, String dateDebut, String dateFin, String theme, String etat, Set<Soumission> soumissions, UtilisateurDTO utilisateur) {
        this.id = id;
        this.titre = titre;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.theme = theme;
        this.etat = etat;
        this.soumissions = soumissions;
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

    public Set<Soumission> getSoumissions() {
        return soumissions;
    }

    public void setSoumissions(Set<Soumission> soumissions) {
        this.soumissions = soumissions;
    }

    public UtilisateurDTO getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(UtilisateurDTO utilisateur) {
        this.utilisateur = utilisateur;
    }
}
