package tp.isilB.conferenceles.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Conference {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String titre;
    private String dateDebut;
    private String dateFin;
    private String theme;
    private String etat;  // ouverte aux soummision, fermee, en evaluation etc..
    @OneToMany(mappedBy = "conference", cascade = CascadeType.ALL)
    private Set<Soumission> soumissions = new HashSet<>();
    @ManyToOne
    private Utilisateur utilisateur;



    public Conference() {}
    public Conference(String titre, String dateDebut, String dateFin, String theme, String etat) {
        this.titre = titre;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.theme = theme;
        this.etat = etat;
    }

    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }
    public Utilisateur getUtilisateur() { return utilisateur; }
    public int getId() {return id;}
    public String getTitre() {return titre;}
    public String getDateDebut() {return dateDebut;}
    public String getDateFin() {return dateFin;}
    public String getTheme() {return theme;}
    public String getEtat() {return etat;}
    public void setTitre(String titre) {this.titre = titre;}
    public void setDateDebut(String dateDebut) {this.dateDebut = dateDebut;}
    public void setDateFin(String dateFin) {this.dateFin = dateFin;}
    public void setTheme(String theme) {this.theme = theme;}
    public void setEtat(String etat) {this.etat = etat;}
    public void setSoumissions(Set<Soumission> soumissions) { this.soumissions = soumissions;}
    public Collection<Soumission> getSoumissions() {return soumissions;}

    public void addSoumission(Soumission soumission) {
        this.soumissions.add(soumission);
        soumission.setConference(this);
    }
    public void removeSoumission(Soumission soumission) { this.soumissions.remove(soumission); }
}
