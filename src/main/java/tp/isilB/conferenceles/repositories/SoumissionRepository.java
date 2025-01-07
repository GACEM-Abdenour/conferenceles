package tp.isilB.conferenceles.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tp.isilB.conferenceles.dto.SoumissionDTO;
import tp.isilB.conferenceles.entities.Conference;
import tp.isilB.conferenceles.entities.Soumission;

import java.util.List;

public interface SoumissionRepository extends JpaRepository<Soumission, Long> {
}
