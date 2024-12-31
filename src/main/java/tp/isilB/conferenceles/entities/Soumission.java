package tp.isilB.conferenceles.entities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashSet;


@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"nom", "description", "utilisateur_id"})
)
public class Soumission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nom;
    private String description;

    @ManyToOne
    private Utilisateur utilisateur;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "details_id")
    private DetailsSoumission detailsSoumission;
    @OneToMany(mappedBy = "soumission", cascade = CascadeType.ALL)
    private Collection<Evaluation> evaluations = new HashSet<>();
    @ManyToOne
    private Conference conference;


    public Soumission(String nom, String description, Utilisateur utilisateur) {
        super();
        this.nom = nom;
        this.description = description;
        this.utilisateur = utilisateur;

    }

    public Soumission() { }

    public int getId() {
        return id;
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

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }
    public Conference getConference() { return conference; }

    public void setDetailsSoumission(DetailsSoumission detailsSoumission) {
        this.detailsSoumission = detailsSoumission;
    }
}
