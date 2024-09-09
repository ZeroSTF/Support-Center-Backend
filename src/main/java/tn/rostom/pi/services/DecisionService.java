package tn.rostom.pi.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import tn.rostom.pi.entities.Decision;
import tn.rostom.pi.entities.Reclamation;
import tn.rostom.pi.entities.User;
import tn.rostom.pi.repositories.DecisionRepository;
import tn.rostom.pi.repositories.ReclamationRepository;
import tn.rostom.pi.repositories.UserRepository;
import tn.rostom.pi.services.IServices.IDecisionService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DecisionService implements IDecisionService {
    private final DecisionRepository decisionRepository;
    private final ReclamationRepository reclamationRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Decision addDecision(Decision decision, Long reclamationId, Long adminId) {
        Reclamation reclamation = reclamationRepository.findById(reclamationId)
            .orElseThrow(() -> new EntityNotFoundException("Reclamation not found with id: " + reclamationId));
        
        User admin = userRepository.findById(adminId)
            .orElseThrow(() -> new EntityNotFoundException("Admin not found with id: " + adminId));
        
        decision.setReclamation(reclamation);
        decision.setAdmin(admin);
        decision.setDate(LocalDateTime.now());
        
        reclamation.setStatus("Closed");
        reclamationRepository.save(reclamation);
        
        return decisionRepository.save(decision);
    }

    @Override
    public Decision updateDecision(Decision decision, Long reclamationId, Long adminId) {
        Reclamation reclamation = reclamationRepository.findById(reclamationId)
                .orElseThrow(() -> new EntityNotFoundException("Reclamation not found with id: " + reclamationId));

        User admin = userRepository.findById(adminId)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found with id: " + adminId));

        log.info("reclamation is: " + reclamation);
        decision.setReclamation(reclamation);
        decision.setAdmin(admin);
        return decisionRepository.save(decision);
    }

    @Override
    public boolean deleteDecision(Long id) {
        if(!decisionRepository.existsById(id))
            return false;
        decisionRepository.deleteById(id);
        return true;
    }

    @Override
    public Decision getDecision(Long id) {
        return decisionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Decision> getDecisions() {
        return decisionRepository.findAll();
    }

    @Override
    public Decision getDecisionByRecId(Long reclamationId) {
        return decisionRepository.findByReclamationId(reclamationId);
    }
}
