package tp.isilB.conferenceles.dto;

public class EvaluationForSoumissionDTO {
    private int note;
    private int evaluatorId;


    public EvaluationForSoumissionDTO(int note, int evaluatorId) {
        this.note = note;
        this.evaluatorId = evaluatorId;
    }


    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getEvaluatorId() {
        return evaluatorId;
    }

    public void setEvaluatorId(int evaluatorId) {
        this.evaluatorId = evaluatorId;
    }
}