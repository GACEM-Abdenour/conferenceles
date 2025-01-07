package tp.isilB.conferenceles.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import tp.isilB.conferenceles.Services.EvaluationService;
import tp.isilB.conferenceles.Services.SoumissionService;
import tp.isilB.conferenceles.dto.EvaluationDTO;
import tp.isilB.conferenceles.dto.EvaluationResponseDTO;
import tp.isilB.conferenceles.entities.Evaluation;
import tp.isilB.conferenceles.entities.RoleType;
import tp.isilB.conferenceles.entities.Soumission;
import tp.isilB.conferenceles.entities.Utilisateur;
import tp.isilB.conferenceles.repositories.EvaluationRepository;
import tp.isilB.conferenceles.repositories.UtilisateurRepository;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/evaluations")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private UtilisateurRepository utilisateurRepository;


    @Autowired
    private EvaluationRepository evaluationRepository;


    @PostMapping("/{utilisateurId}/{soumissionId}")
    public ResponseEntity<?> evaluateSoumission(
            @PathVariable int utilisateurId,
            @PathVariable int soumissionId,
            @RequestBody Evaluation evaluation) {

        if (evaluation.getNote() < 0 || evaluation.getNote() > 10) {
            return ResponseEntity.badRequest().body("Note must be between 0 and 10");
        }

        if (evaluationRepository.existsByUtilisateurIdAndSoumissionId(utilisateurId, soumissionId)) {
            return ResponseEntity.badRequest().body("This evaluator has already evaluated the submission.");
        }

        EvaluationResponseDTO evaluationResponse = evaluationService.evaluateSoumission(
                utilisateurId,
                soumissionId,
                evaluation.getNote(),
                evaluation.getCommentaires(),
                evaluation.getEtat()
        );
        return new ResponseEntity<>(evaluationResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EvaluationResponseDTO>> getAllEvaluations() {
        List<EvaluationResponseDTO> evaluations = evaluationService.getAllEvaluations();
        return new ResponseEntity<>(evaluations, HttpStatus.OK);
    }


}
