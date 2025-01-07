package tp.isilB.conferenceles.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import tp.isilB.conferenceles.entities.Evaluation;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    boolean existsByUtilisateurIdAndSoumissionId(int utilisateurId, int soumissionId);
}
