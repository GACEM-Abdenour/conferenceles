package tp.isilB.conferenceles.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.isilB.conferenceles.entities.Conference;

public interface ConferenceRepository extends JpaRepository<Conference, Long> {
}
