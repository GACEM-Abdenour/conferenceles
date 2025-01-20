package tp.isilB.conferenceles.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp.isilB.conferenceles.entities.RoleType;
import tp.isilB.conferenceles.entities.Utilisateur;
import tp.isilB.conferenceles.repositories.UtilisateurRepository;

import java.util.List;
import java.util.Set;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

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

    public void deleteUtilisateur(int id) {
        Utilisateur utilisateur = utilisateurRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("User not found"));

        utilisateurRepository.delete(utilisateur);
    }

    public void deleteAllUtilisateurs() {
        utilisateurRepository.deleteAll();
    }

}