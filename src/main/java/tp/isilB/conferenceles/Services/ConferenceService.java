package tp.isilB.conferenceles.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp.isilB.conferenceles.repositories.*;
import tp.isilB.conferenceles.entities.*;

import java.util.List;

@Service
public class ConferenceService {

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Conference createConference(int utilisateurId, Conference conference) {
        Utilisateur utilisateur = utilisateurRepository.findById((long) utilisateurId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (utilisateur.hasRole(RoleType.EDITEUR)) {
            utilisateur.addConference(conference);
            return conferenceRepository.save(conference);
        } else {
            throw new RuntimeException("Only editors can create conferences.");
        }
    }


    public List<Conference> getAllConferences() {
        return (List<Conference>) conferenceRepository.findAll();
    }

    public Conference getConferenceById(int id) {
        return conferenceRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Conference not found"));
    }
}
