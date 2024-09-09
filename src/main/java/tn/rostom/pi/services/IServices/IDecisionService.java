package tn.rostom.pi.services.IServices;

import tn.rostom.pi.entities.Decision;

import java.util.List;

public interface IDecisionService {
    Decision addDecision(Decision decision, Long reclamationId, Long adminId);
    Decision updateDecision(Decision decision, Long reclamationId, Long adminId);
    boolean deleteDecision(Long id);
    Decision getDecision(Long id);
    List<Decision> getDecisions();
    Decision getDecisionByRecId(Long reclamationId);
}
