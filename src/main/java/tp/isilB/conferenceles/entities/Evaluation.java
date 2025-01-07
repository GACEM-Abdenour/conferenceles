package tp.isilB.conferenceles.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "evaluations", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"utilisateur_id", "soumission_id"})
})
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int note;
    private String commentaires;
    private String etat;
    @ManyToOne
    private Soumission soumission;
    @ManyToOne
    private Utilisateur utilisateur;

    public Evaluation(int note, String commentaires, String etat, Soumission soumission) {
        this.note = note;
        this.commentaires = commentaires;
        this.etat = etat;
        this.soumission = soumission;
    }

    public Evaluation() {}

    public void setNote(int note) { this.note = note; }
    public void setCommentaires(String commentaires) { this.commentaires = commentaires; }
    public void setEtat(String etat) { this.etat = etat; }
    public void setSoumission(Soumission soumission) { this.soumission = soumission; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }
    public int getId() { return id; }
    public int getNote() { return note; }
    public String getCommentaires() { return commentaires; }
    public String getEtat() { return etat; }
    public Soumission getSoumission() { return soumission; }
    public Utilisateur getUtilisateur() { return utilisateur; }
}