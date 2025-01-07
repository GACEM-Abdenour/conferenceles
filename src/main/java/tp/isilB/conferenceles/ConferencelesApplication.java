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


		Utilisateur auteur1 = new Utilisateur("etudiant1", "nomEtd1", "ysthb" );
		Utilisateur auteur2 = new Utilisateur("etudiant2", "nomEtd2", "ESI" );
		Utilisateur auteur3 = new Utilisateur("etudiant3", "nomEtd3", "Alger" );

		auteur1.addRole(RoleType.AUTEUR);
		auteur1.addRole(RoleType.EVALUATEUR);
		auteur1.addRole(RoleType.EDITEUR);
		auteur2.addRole(RoleType.AUTEUR);
		auteur3.addRole(RoleType.EVALUATEUR);

		utilisateurRepository.save(auteur1);
		utilisateurRepository.save(auteur2);
		utilisateurRepository.save(auteur3);

	}
}
