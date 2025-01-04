package tp.isilB.conferenceles.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @OneToMany(cascade=CascadeType.ALL,mappedBy = "utilisateur")
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

    public void setnom(String nom) {
        this.nom = nom;
    }
    public void setprenom(String prenom) { this.prenom = prenom; }
    public void setinfos(String infos) { this.infos = infos; }
    public String getnom() { return nom; }
    public String getprenom() { return prenom; }
    public String getinfos() { return infos; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Collection<Soumission> getSoumissions() { return soumissions; }
    public void SetSoumissions(Set<Soumission> soumissions) { this.soumissions= soumissions;}
    public Collection<Conference> getConferences() { return conferences; }
    public void SetConferences(Set<Conference> conferences) { this.conferences= conferences; }

    public Set<RoleType> getRoles() { return roles; }
    public void SetRoles(Set<RoleType> roles) { this.roles= roles; }
    public void addEvaluation(Evaluation evaluation) {
        this.evaluations.add(evaluation);
        evaluation.setUtilisateur(this);
    }
    public void addESoumission(Soumission soumission) {
        this.soumissions.add(soumission);
        soumission.setUtilisateur(this);
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


}
