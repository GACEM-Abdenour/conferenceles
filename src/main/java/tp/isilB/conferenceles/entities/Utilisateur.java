package tp.isilB.conferenceles.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nom;
    private String prenom;
    @Column(name = "infos", nullable = false, length = 256)
    private String infos;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Soumission> soumissions = new HashSet<>();

    @ElementCollection(targetClass = RoleType.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "utilisateur_roles", joinColumns = @JoinColumn(name = "utilisateur_id"))
    @Column(name = "role")
    private Set<RoleType> roles = new HashSet<>();

    @OneToMany
    private Set<Evaluation> evaluations = new HashSet<>();

    @OneToMany
    private Set<Conference> conferences = new HashSet<>();

    public Utilisateur(String nom, String prenom, String infos) {
        this.nom = nom;
        this.prenom = prenom;
        this.infos = infos;
    }

    public Utilisateur() { }

    public int getId() { return id; }
    public void setnom(String nom) { this.nom = nom; }
    public void setprenom(String prenom) { this.prenom = prenom; }
    public void setinfos(String infos) { this.infos = infos; }
    public String getnom() { return nom; }
    public String getprenom() { return prenom; }
    public String getinfos() { return infos; }
    public Collection<Soumission> getSoumissions() { return soumissions; }
    public void addSoumission(Soumission soumission, Conference conference) {
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
    public void addRole(RoleType role) { this.roles.add(role); }
    public void restorRoles() { roles.removeAll(roles); }
    public boolean hasRole(RoleType role) { return this.roles.contains(role); }
    public Set<RoleType> getRoles() { return roles; }
}