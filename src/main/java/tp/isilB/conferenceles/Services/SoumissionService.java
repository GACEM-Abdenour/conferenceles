package tp.isilB.conferenceles.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp.isilB.conferenceles.repositories.*;
import tp.isilB.conferenceles.entities.*;

import java.util.List;


@Service
public class SoumissionService {

    @Autowired
    private SoumissionRepository soumissionRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private ConferenceRepository conferenceRepository;

    public Soumission createSoumission(int utilisateurId, int conferenceId, Soumission soumission) {
        Utilisateur utilisateur = utilisateurRepository.findById((long) utilisateurId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Conference conference = conferenceRepository.findById((long) conferenceId)
                .orElseThrow(() -> new RuntimeException("Conference not found"));
        if (utilisateur.hasRole(RoleType.AUTEUR)) {
            utilisateur.addSoumission(soumission,conference);
            return soumissionRepository.save(soumission);
        } else {
            throw new RuntimeException("Only authors can create submissions.");
        }
    }

    public List<Soumission> getAllSoumissions() {
        return (List<Soumission>) soumissionRepository.findAll();
    }

    public Soumission getSoumissionById(int id) {
        return soumissionRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Submission not found"));
    }
}
