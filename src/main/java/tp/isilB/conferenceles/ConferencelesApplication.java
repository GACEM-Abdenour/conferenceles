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
	private SoumisionRepository soumissionRepository;
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


		Conference conference1 = auteur1.createConference("First conf", "2001-01-01", "2001-09-11", "Tech", "waiting to be set up");

		conferenceRepository.save(conference1);
		utilisateurRepository.save(auteur1);

		Soumission soumission1 = auteur1.createSubmission("soumession1","Sec",auteur1,conference1);
		Soumission soumission2 = new Soumission("soumission2", "IA", auteur1);
		Soumission soumission3 = new Soumission("soumission3", "big data", auteur1);
		Soumission soumission4 = new Soumission("soumission4", "IL", auteur2);



		conference1.addSoumission(soumission1);
		conference1.addSoumission(soumission2);
		conference1.addSoumission(soumission3);
		conference1.addSoumission(soumission4);



		soumission1.setDetailsSoumission(new DetailsSoumission("12-11-2024", "15-11-2024"));
		soumission2.setDetailsSoumission(new DetailsSoumission("12-11-2023", "15-11-2023"));
		soumission3.setDetailsSoumission(new DetailsSoumission("12-11-2022", "15-11-2022"));
		soumission4.setDetailsSoumission(new DetailsSoumission("12-11-2021", "15-11-2021"));

		soumissionRepository.save(soumission1);
		soumissionRepository.save(soumission2);
		soumissionRepository.save(soumission3);
		soumissionRepository.save(soumission4);

		Evaluation evaluation = new Evaluation(9, "is this your best?", "rated",soumission1);
		Evaluation evaluation1 = auteur3.evaluateSubmission(soumission1,4,"bruh","rated");

		evaluationRepository.save(evaluation);
		evaluationRepository.save(evaluation1);
		utilisateurRepository.save(auteur3);
	}
}
