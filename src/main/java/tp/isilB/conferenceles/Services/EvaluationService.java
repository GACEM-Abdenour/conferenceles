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

    //hna update( PUT )
    public EvaluationResponseDTO updateEvaluation(int id, Evaluation evaluationDetails, int currentUtilisateurId) {
        Evaluation evaluation = evaluationRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Evaluation not found"));


        if (evaluation.getUtilisateur().getId() != currentUtilisateurId) {
            throw new RuntimeException("Only the evaluator who created the evaluation can update it.");
        }


        evaluation.setNote(evaluationDetails.getNote());
        evaluation.setCommentaires(evaluationDetails.getCommentaires());
        evaluation.setEtat(evaluationDetails.getEtat());


        Evaluation updatedEvaluation = evaluationRepository.save(evaluation);


        return new EvaluationResponseDTO(
                updatedEvaluation.getId(),
                updatedEvaluation.getNote(),
                updatedEvaluation.getCommentaires(),
                updatedEvaluation.getEtat(),
                updatedEvaluation.getUtilisateur().getId(),
                updatedEvaluation.getSoumission().getId()
        );
    }


    public void deleteEvaluation(int id) {
        Evaluation evaluation = evaluationRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Evaluation not found"));

        evaluationRepository.delete(evaluation);
    }

}