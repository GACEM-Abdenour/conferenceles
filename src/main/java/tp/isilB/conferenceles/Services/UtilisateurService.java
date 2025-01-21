package tp.isilB.conferenceles.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp.isilB.conferenceles.entities.*;
import tp.isilB.conferenceles.repositories.ConferenceRepository;
import tp.isilB.conferenceles.repositories.EvaluationRepository;
import tp.isilB.conferenceles.repositories.SoumissionRepository;
import tp.isilB.conferenceles.repositories.UtilisateurRepository;

import java.util.List;
import java.util.Set;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Autowired
    private SoumissionRepository soumissionRepository;

    @Autowired
    private EvaluationRepository evaluationRepository;


    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur getUtilisateurById(int id) {
        return utilisateurRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Set<RoleType> getUserRoles(int id) {
        Utilisateur utilisateur = utilisateurRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return utilisateur.getRoles();
    }

    public List<Utilisateur> getAllUtilisateurs() {
        return (List<Utilisateur>) utilisateurRepository.findAll();
    }

    public Utilisateur updateUtilisateur(int id, Utilisateur utilisateurDetails) {
        Utilisateur utilisateur = utilisateurRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("User not found"));

        utilisateur.setnom(utilisateurDetails.getnom());
        utilisateur.setprenom(utilisateurDetails.getprenom());
        utilisateur.setinfos(utilisateurDetails.getinfos());
        utilisateur.setRoles(utilisateurDetails.getRoles());

        return utilisateurRepository.save(utilisateur);
    }

    public void deleteUtilisateur(Long id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        for (Evaluation evaluation : utilisateur.getEvaluations()) {
            evaluation.setUtilisateur(null);
            evaluationRepository.save(evaluation);
        }

        for (Soumission soumission : utilisateur.getSoumissions()) {
            soumission.setUtilisateur(null);
            soumissionRepository.save(soumission);
        }

        for (Conference conference : utilisateur.getConferences()) {
            conference.setUtilisateur(null);
            conferenceRepository.save(conference);
        }

        utilisateurRepository.delete(utilisateur);
    }


    public void deleteAllUtilisateurs() {
        utilisateurRepository.deleteAll();
    }

}