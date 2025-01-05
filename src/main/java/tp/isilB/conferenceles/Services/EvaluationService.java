package tp.isilB.conferenceles.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp.isilB.conferenceles.entities.*;
import tp.isilB.conferenceles.repositories.EvaluationRepository;
import tp.isilB.conferenceles.repositories.SoumissionRepository;
import tp.isilB.conferenceles.repositories.UtilisateurRepository;

import java.util.List;
import java.util.Set;

@Service
public class EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private SoumissionRepository soumissionRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;


    public Evaluation evaluateSoumission(int utilisateurId, int soumissionId, int note, String commentaires, String etat) {

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
            return evaluationRepository.save(evaluation);
        } else {
            throw new RuntimeException("Only Evaleuteurs can create submissions.");
        }

    }

    public List<Evaluation> getAllEvaluations() {
        return (List<Evaluation>) evaluationRepository.findAll();
    }
}
