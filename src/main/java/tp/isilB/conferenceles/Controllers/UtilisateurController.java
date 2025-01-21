package tp.isilB.conferenceles.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tp.isilB.conferenceles.entities.Conference;
import tp.isilB.conferenceles.entities.RoleType;
import tp.isilB.conferenceles.entities.Soumission;
import tp.isilB.conferenceles.entities.Utilisateur;
import tp.isilB.conferenceles.Services.UtilisateurService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping
    public ResponseEntity<Utilisateur> createUtilisateur(@RequestBody Utilisateur utilisateur) {
        Utilisateur createdUtilisateur = utilisateurService.createUtilisateur(utilisateur);
        return new ResponseEntity<>(createdUtilisateur, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable int id) {
        Utilisateur utilisateur = utilisateurService.getUtilisateurById(id);
        return new ResponseEntity<>(utilisateur, HttpStatus.OK);
    }

    @GetMapping("/{id}/roles")
    public ResponseEntity<Set<RoleType>> getUserRoles(@PathVariable int id) {
        Set<RoleType> roles = utilisateurService.getUserRoles(id);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Utilisateur>> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = utilisateurService.getAllUtilisateurs();
        return new ResponseEntity<>(utilisateurs, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable int id, @RequestBody Utilisateur utilisateurDetails) {
        Utilisateur updatedUtilisateur = utilisateurService.updateUtilisateur(id, utilisateurDetails);
        return new ResponseEntity<>(updatedUtilisateur, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable int id) {
        utilisateurService.deleteUtilisateur((long) id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllUtilisateurs() {
        utilisateurService.deleteAllUtilisateurs();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}