package tp.isilB.conferenceles.repositories;

import org.springframework.data.repository.CrudRepository;
import tp.isilB.conferenceles.entities.Utilisateur;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Long> {
}
