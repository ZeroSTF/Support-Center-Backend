package tn.rostom.pi.services;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tn.rostom.pi.entities.Decision;
import tn.rostom.pi.entities.Reclamation;
import tn.rostom.pi.entities.User;
import tn.rostom.pi.repositories.DecisionRepository;
import tn.rostom.pi.repositories.ReclamationRepository;
import tn.rostom.pi.repositories.UserRepository;
import tn.rostom.pi.services.IServices.IReclamationService;

@Service
@RequiredArgsConstructor
public class ReclamationService implements IReclamationService {
    private final ReclamationRepository reclamationRepository;
    private final DecisionRepository decisionRepository;
    private final UserRepository userRepository;

    @Override
    public Reclamation createReclamation(Reclamation reclamation, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        reclamation.setUser(user);
        reclamation.setDate(LocalDateTime.now());
        reclamation.setStatus("Pending");
        return reclamationRepository.save(reclamation);
    }

    @Override
    public Reclamation updateReclamation(Reclamation reclamation) {
        return reclamationRepository.save(reclamation);
    }

    @Override
    public Reclamation getReclamationById(Long id) {
        return reclamationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reclamation not found with id: " + id));
    }

    @Override
    public boolean deleteReclamation(Long id) {
        if (!reclamationRepository.existsById(id))
            return false;
        reclamationRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Reclamation> getAllReclamations() {
        return reclamationRepository.findAll();
    }

    @Override
    public List<Reclamation> getReclamationsByUserId(Long userId) {
        return reclamationRepository.findByUserId(userId);
    }

}
