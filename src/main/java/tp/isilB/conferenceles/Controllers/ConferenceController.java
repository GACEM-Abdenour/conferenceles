package tp.isilB.conferenceles.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import tp.isilB.conferenceles.Services.ConferenceService;
import tp.isilB.conferenceles.dto.ConferenceDTO;
import tp.isilB.conferenceles.dto.UtilisateurDTO;
import tp.isilB.conferenceles.entities.*;
import tp.isilB.conferenceles.repositories.ConferenceRepository;

import java.util.List;
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
    /*
    @GetMapping
    public ResponseEntity<List<Conference>> getAllConferences() {
        List<Conference> conferences = conferenceService.getAllConferences();
        return new ResponseEntity<>(conferences, HttpStatus.OK);
    }
     */

    @GetMapping
    public List<ConferenceDTO> getAllConferences() {
        return conferenceRepository.findAll().stream().map(conference -> {
            UtilisateurDTO utilisateurDTO = new UtilisateurDTO(conference.getUtilisateur().getId());
            return new ConferenceDTO(
                    conference.getId(),
                    conference.getTitre(),
                    conference.getDateDebut(),
                    conference.getDateFin(),
                    conference.getTheme(),
                    conference.getEtat(),
                    conference.getSoumissions(),
                    utilisateurDTO
            );
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conference> getConferenceById(@PathVariable int id) {
        Conference conference = conferenceService.getConferenceById(id);
        return new ResponseEntity<>(conference, HttpStatus.OK);
    }
}