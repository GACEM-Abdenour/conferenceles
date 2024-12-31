package tp.isilB.conferenceles.entities;

import jakarta.persistence.*;

@Entity
public class DetailsSoumission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String dateDeSoumission;
    private String dateDeModification;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "soumission_id")
    private Soumission soumission;

    public DetailsSoumission(String dateDeSoumission, String dateDeModification) {
        super();
        this.dateDeSoumission = dateDeSoumission;
        this.dateDeModification = dateDeModification;
    }
    public DetailsSoumission() {}

    public int getId() {return id;}
    public String getDateDeSoumission() {return dateDeSoumission;}
    public void setDateDeSoumission(String dateDeSoumission) { this.dateDeSoumission = dateDeSoumission;}
    public String getDateDeModification() {return dateDeModification;}
    public void setDateDeModification(String dateDeModification) { this.dateDeModification = dateDeModification;}
}
