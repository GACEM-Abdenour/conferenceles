package tp.isilB.conferenceles.dtos;

public class UtilisateurDTO {
    private int id;
    private String nom;
    private String prenom;
    private String infos;

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getInfos() { return infos; }
    public void setInfos(String infos) { this.infos = infos; }
}