package tp.isilB.conferenceles.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp.isilB.conferenceles.dto.EvaluationForSoumissionDTO;
import tp.isilB.conferenceles.dto.SoumissionWithDetailsDTO;
import tp.isilB.conferenceles.repositories.ConferenceRepository;
import tp.isilB.conferenceles.repositories.SoumissionRepository;
import tp.isilB.conferenceles.repositories.UtilisateurRepository;
import tp.isilB.conferenceles.entities.RoleType;
import tp.isilB.conferenceles.entities.Soumission;
import tp.isilB.conferenceles.entities.Utilisateur;
import tp.isilB.conferenceles.entities.Conference;

import java.util.List;
import java.util.stream.Collectors;

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
            utilisateur.addSoumission(soumission, conference);
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

    public List<SoumissionWithDetailsDTO> getAllSoumissionsWithDetails() {
        return soumissionRepository.findAll().stream()
                .map(soumission -> new SoumissionWithDetailsDTO(
                        soumission.getId(),
                        soumission.getNom(),
                        soumission.getDescription(),
                        soumission.getConference().getId(),
                        soumission.getUtilisateur().getId(),
                        soumission.getEvaluations().stream()
                                .map(evaluation -> new EvaluationForSoumissionDTO(
                                        evaluation.getNote(),
                                        evaluation.getUtilisateur().getId()
                                ))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }
}