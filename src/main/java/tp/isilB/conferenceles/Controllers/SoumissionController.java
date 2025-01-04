package tp.isilB.conferenceles.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import tp.isilB.conferenceles.Services.SoumissionService;
import tp.isilB.conferenceles.entities.*;
import java.util.List;

@RestController
@RequestMapping("/api/soumissions")
public class SoumissionController {

    @Autowired
    private SoumissionService soumissionService;

    @PostMapping("/{utilisateurId}/{conferenceId}")
    public ResponseEntity<Soumission> createSoumission(@PathVariable int utilisateurId, @PathVariable int conferenceId, @RequestBody Soumission soumission) {
        Soumission createdSoumission = soumissionService.createSoumission(utilisateurId, conferenceId, soumission);
        return new ResponseEntity<>(createdSoumission, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Soumission>> getAllSoumissions() {
        List<Soumission> soumissions = soumissionService.getAllSoumissions();
        return new ResponseEntity<>(soumissions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Soumission> getSoumissionById(@PathVariable int id) {
        Soumission soumission = soumissionService.getSoumissionById(id);
        return new ResponseEntity<>(soumission, HttpStatus.OK);
    }
}