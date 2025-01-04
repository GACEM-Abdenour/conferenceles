package tp.isilB.conferenceles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tp.isilB.conferenceles.entities.*;
import tp.isilB.conferenceles.repositories.*;
import tp.isilB.conferenceles.repositories.UtilisateurRepository;

import javax.management.relation.Role;


@SpringBootApplication
public class ConferencelesApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ConferencelesApplication.class, args);
	}
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private SoumissionRepository soumissionRepository;
	@Autowired
	private ConferenceRepository conferenceRepository;
	@Autowired
	private EvaluationRepository evaluationRepository;

	@Override
	public void run(String... args) throws Exception {


	}
}
