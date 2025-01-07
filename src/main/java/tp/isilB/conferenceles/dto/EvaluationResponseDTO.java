package tp.isilB.conferenceles.dto;

public class EvaluationResponseDTO {
    private int id;
    private int note;
    private String commentaires;
    private String etat;
    private int utilisateurId;
    private int soumissionId;


    public EvaluationResponseDTO(int id, int note, String commentaires, String etat, int utilisateurId, int soumissionId) {
        this.id = id;
        this.note = note;
        this.commentaires = commentaires;
        this.etat = etat;
        this.utilisateurId = utilisateurId;
        this.soumissionId = soumissionId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(int utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public int getSoumissionId() {
        return soumissionId;
    }

    public void setSoumissionId(int soumissionId) {
        this.soumissionId = soumissionId;
    }
}