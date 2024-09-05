package tn.rostom.pi.services.IServices;

import tn.rostom.pi.entities.Decision;

import java.util.List;

public interface IDecisionService {
    Decision addDecision(Decision decision, Long reclamationId, Long adminId);
    Decision updateDecision(Decision decision);
    boolean deleteDecision(Long id);
    Decision getDecision(Long id);
    List<Decision> getDecisions();
}
