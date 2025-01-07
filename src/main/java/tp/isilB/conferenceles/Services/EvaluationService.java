package tp.isilB.conferenceles.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp.isilB.conferenceles.dto.EvaluationResponseDTO;
import tp.isilB.conferenceles.entities.Evaluation;
import tp.isilB.conferenceles.entities.RoleType;
import tp.isilB.conferenceles.entities.Soumission;
import tp.isilB.conferenceles.entities.Utilisateur;
import tp.isilB.conferenceles.repositories.EvaluationRepository;
import tp.isilB.conferenceles.repositories.SoumissionRepository;
import tp.isilB.conferenceles.repositories.UtilisateurRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private SoumissionRepository soumissionRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public EvaluationResponseDTO evaluateSoumission(int utilisateurId, int soumissionId, int note, String commentaires, String etat) {
        Utilisateur utilisateur = utilisateurRepository.findById((long) utilisateurId)
                .orElseThrow(() -> new RuntimeException("Evaluator not found"));

        Soumission soumission = soumissionRepository.findById((long) soumissionId)
                .orElseThrow(() -> new RuntimeException("Submission not found"));

        if (soumission.getUtilisateur().getId() == utilisateurId) {
            throw new RuntimeException("An author cannot evaluate their own submission.");
        }

        if (utilisateur.hasRole(RoleType.EVALUATEUR)) {
            Evaluation evaluation = new Evaluation(note, commentaires, etat, soumission);
            evaluation.setUtilisateur(utilisateur);
            Evaluation savedEvaluation = evaluationRepository.save(evaluation);

            // Map the saved evaluation to EvaluationResponseDTO
            return new EvaluationResponseDTO(
                    savedEvaluation.getId(),
                    savedEvaluation.getNote(),
                    savedEvaluation.getCommentaires(),
                    savedEvaluation.getEtat(),
                    savedEvaluation.getUtilisateur().getId(),
                    savedEvaluation.getSoumission().getId()
            );
        } else {
            throw new RuntimeException("Only evaluators can evaluate submissions.");
        }
    }

    public List<EvaluationResponseDTO> getAllEvaluations() {
        return evaluationRepository.findAll().stream()
                .map(evaluation -> new EvaluationResponseDTO(
                        evaluation.getId(),
                        evaluation.getNote(),
                        evaluation.getCommentaires(),
                        evaluation.getEtat(),
                        evaluation.getUtilisateur().getId(),
                        evaluation.getSoumission().getId()
                ))
                .collect(Collectors.toList());
    }
}