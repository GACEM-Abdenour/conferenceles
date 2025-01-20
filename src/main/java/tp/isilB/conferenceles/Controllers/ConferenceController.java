package tp.isilB.conferenceles.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import tp.isilB.conferenceles.Services.ConferenceService;
import tp.isilB.conferenceles.dto.ConferenceDTO;
import tp.isilB.conferenceles.dto.UtilisateurDTO;
import tp.isilB.conferenceles.entities.Conference;
import tp.isilB.conferenceles.repositories.ConferenceRepository;
import tp.isilB.conferenceles.entities.Soumission;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/conferences")
public class ConferenceController {

    @Autowired
    private ConferenceService conferenceService;
    @Autowired
    private ConferenceRepository conferenceRepository;

    @PostMapping("/{utilisateurId}")
    public ResponseEntity<Conference> createConference(@PathVariable int utilisateurId, @RequestBody Conference conference) {
        Conference createdConference = conferenceService.createConference(utilisateurId, conference);
        return new ResponseEntity<>(createdConference, HttpStatus.CREATED);
    }

    @GetMapping
    public List<ConferenceDTO> getAllConferences() {
        return conferenceRepository.findAll().stream().map(conference -> {
            UtilisateurDTO utilisateurDTO = new UtilisateurDTO(conference.getUtilisateur().getId());


            Set<Integer> soumissionIds = conference.getSoumissions().stream()
                    .map(Soumission::getId)
                    .collect(Collectors.toSet());

            return new ConferenceDTO(
                    conference.getId(),
                    conference.getTitre(),
                    conference.getDateDebut(),
                    conference.getDateFin(),
                    conference.getTheme(),
                    conference.getEtat(),
                    soumissionIds, // List of submission IDs
                    utilisateurDTO // Single UtilisateurDTO object
            );
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conference> getConferenceById(@PathVariable int id) {
        Conference conference = conferenceService.getConferenceById(id);
        return new ResponseEntity<>(conference, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conference> updateConference(@PathVariable int id, @RequestBody Conference conferenceDetails) {
        Conference updatedConference = conferenceService.updateConference(id, conferenceDetails);
        return new ResponseEntity<>(updatedConference, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConference(@PathVariable int id) {
        conferenceService.deleteConference(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllConferences() {
        conferenceService.deleteAllConference();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}