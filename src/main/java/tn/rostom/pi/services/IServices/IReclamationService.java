package tn.rostom.pi.services.IServices;

import java.util.List;

import tn.rostom.pi.entities.Decision;
import tn.rostom.pi.entities.Reclamation;
import tn.rostom.pi.entities.User;

public interface IReclamationService {
    Reclamation createReclamation(Reclamation reclamation, Long userId);

    Reclamation updateReclamation(Reclamation reclamation);

    Reclamation getReclamationById(Long id);

    List<Reclamation> getAllReclamations();

    List<Reclamation> getReclamationsByUserId(Long userId);

    boolean deleteReclamation(Long id);

    Decision makeDecision(Long reclamationId, Decision decision, User admin);
}
