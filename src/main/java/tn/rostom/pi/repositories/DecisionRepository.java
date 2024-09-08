package tn.rostom.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.rostom.pi.entities.Decision;

public interface DecisionRepository extends JpaRepository<Decision, Long> {
    Decision findByReclamationId(Long reclamationId);
}
