package tp.isilB.conferenceles.repositories;

import org.springframework.data.repository.CrudRepository;
import tp.isilB.conferenceles.entities.Conference;

public interface ConferenceRepository extends CrudRepository <Conference, Long>{
}
