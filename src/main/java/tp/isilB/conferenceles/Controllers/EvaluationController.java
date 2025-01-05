package tp.isilB.conferenceles.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import tp.isilB.conferenceles.Services.EvaluationService;
import tp.isilB.conferenceles.entities.Evaluation;
import tp.isilB.conferenceles.repositories.EvaluationRepository;

@RestController
@RequestMapping("/api/evaluations")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

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
            return ResponseEntity.badRequest().body("This evaluateur has already evaluated the soumission.");
        }


        Evaluation createdEvaluation = evaluationService.evaluateSoumission(
                utilisateurId,
                soumissionId,
                evaluation.getNote(),
                evaluation.getCommentaires(),
                evaluation.getEtat()
        );
        return new ResponseEntity<>(createdEvaluation, HttpStatus.CREATED);
    }
}
