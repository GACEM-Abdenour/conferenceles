package tp.isilB.conferenceles.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Utilisateur {

    public Utilisateur(String nom, String prenom, String infos) {
        this.nom = nom;
        this.prenom = prenom;
        this.infos = infos;
    }
    public Utilisateur() { }



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;
    private String nom;
    private String prenom;
    @Column(name="infos",nullable=false,length = 256)
    private String infos;
    //submissions
    @JsonIgnore
    @OneToMany(cascade=CascadeType.ALL)
    private Set<Soumission> soumissions = new HashSet<>();
    //roles
    @ElementCollection(targetClass = RoleType.class)
    @Enumerated(EnumType.STRING) // Store enums as strings in the database
    @CollectionTable(name = "utilisateur_roles", joinColumns = @JoinColumn(name = "utilisateur_id"))
    @Column(name = "role")
    private Set<RoleType> roles = new HashSet<>();
    //evaluations
    @OneToMany
    private Set<Evaluation> evaluations = new HashSet<>();
    //conferences
    @OneToMany
    private Set<Conference> conferences = new HashSet<>();

    public int getId() { return id; }
    public void setnom(String nom) {
        this.nom = nom;
    }
    public void setprenom(String prenom) { this.prenom = prenom; }
    public void setinfos(String infos) { this.infos = infos; }
    public String getnom() { return nom; }
    public String getprenom() { return prenom; }
    public String getinfos() { return infos; }
    public Collection<Soumission> getSoumissions() { return soumissions; }
    public void addSoumission(Soumission soumission,Conference conference) {
        this.soumissions.add(soumission);
        conference.getSoumissions().add(soumission);
        soumission.setUtilisateur(this);
        soumission.setConference(conference);
    }
    public void addEvaluation(Evaluation evaluation) {
        this.evaluations.add(evaluation);
        evaluation.setUtilisateur(this);
    }
    public void addConference(Conference conference) {
        this.conferences.add(conference);
        conference.setUtilisateur(this);
    }
    public void addRole(RoleType role) {
        this.roles.add(role);
    }
    public void restorRoles() { roles.removeAll(roles);}

    // check the user's roles
    public boolean hasRole(RoleType role) {
        return this.roles.contains(role);
    }

    // creer une conference
    public Conference createConference(String titre, String dateDebut, String dateFin, String theme, String etat) {

        if ( this.hasRole(RoleType.EDITEUR) ) {

            Conference conference = new Conference(titre,dateDebut,dateFin,theme,etat);
            this.addConference(conference);
             return conference;
        }
        else{
            throw new RuntimeException("Only editors can evaluate submissions.");
        }

    }


    //creer une soumission
    public Soumission createSubmission(String nom, String description, Utilisateur utilisateur,Conference conference) {

        if ( this.hasRole(RoleType.AUTEUR) ) {

            Soumission soumission = new Soumission(nom,description,this);
            this.addSoumission(soumission,conference);
            return soumission;
        }
        else{
            throw new RuntimeException("Only auteurs can create submissions.");
        }

    }

    //evaluer une soumission
    public Evaluation evaluateSubmission(Soumission soumission, int note, String commentaires, String etat) {

        if ( this.hasRole(RoleType.EVALUATEUR ) ){
            if ( !this.hasRole(RoleType.AUTEUR) || !this.getSoumissions().contains(soumission) ){
            Evaluation evaluation = new Evaluation(note,commentaires,etat,soumission);
            this.addEvaluation(evaluation);return evaluation;
            }
            else {
                throw new RuntimeException("An author cannot evaluate his own submission.");
            }
        }
        else{
            throw new RuntimeException("Only evaluators can evaluate submissions.");
        }

    }


    //modifier une soumission
    public void modifySubmission(Soumission soumission, String nom,String description, Utilisateur utilisateur,Conference conference) {
        if ( this.hasRole(RoleType.AUTEUR) && this.getSoumissions().contains(soumission)  ) {
            soumission.setConference(conference);
            soumission.setNom(nom);
            soumission.setDescription(description);
        }
    }

    public void updateConference(Conference conference, String etat) { conference.setEtat(etat); }

    public void updateEvaluation(Evaluation evaluation, String etat) {  evaluation.setEtat(etat); }


    public Set<RoleType> getRoles() { return roles; }
}
