package tp.isilB.conferenceles.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp.isilB.conferenceles.entities.Evaluation;
import tp.isilB.conferenceles.repositories.EvaluationRepository;

import java.util.List;

@Service
public class EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    public Evaluation createEvaluation(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

    public Evaluation getEvaluationById(int id) {
        return evaluationRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Evaluation not found"));
    }

    public Evaluation updateEvaluation(int id, Evaluation evaluation) {
        Evaluation existingEvaluation = evaluationRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Evaluation not found"));
        return evaluationRepository.save(evaluation);
    }

    public void deleteEvaluation(int id) {
        evaluationRepository.deleteById((long) id);
    }

    public List<Evaluation> getAllEvaluations() {
        return (List<Evaluation>) evaluationRepository.findAll();
    }
}