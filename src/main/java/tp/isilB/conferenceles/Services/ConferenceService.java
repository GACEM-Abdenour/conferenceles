package tp.isilB.conferenceles.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp.isilB.conferenceles.repositories.ConferenceRepository;
import tp.isilB.conferenceles.repositories.UtilisateurRepository;
import tp.isilB.conferenceles.entities.Conference;
import tp.isilB.conferenceles.entities.RoleType;
import tp.isilB.conferenceles.entities.Utilisateur;

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

    public Conference updateConference(int id, Conference conferenceDetails) {
        Conference conference = conferenceRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Conference not found"));

        conference.setTitre(conferenceDetails.getTitre());
        conference.setDateDebut(conferenceDetails.getDateDebut());
        conference.setDateFin(conferenceDetails.getDateFin());
        conference.setTheme(conferenceDetails.getTheme());
        conference.setEtat(conferenceDetails.getEtat());

        return conferenceRepository.save(conference);
    }

    public void deleteConference(int id) {
        Conference conference = conferenceRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Conference not found"));

        conferenceRepository.delete(conference);
    }

    public void deleteAllConference() {
        conferenceRepository.deleteAll();
    }
}