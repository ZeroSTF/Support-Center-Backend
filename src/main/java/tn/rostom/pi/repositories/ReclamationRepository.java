package tn.rostom.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.rostom.pi.entities.Reclamation;
import java.util.List;


public interface ReclamationRepository extends JpaRepository<Reclamation, Long> {
    List<Reclamation> findByUserId(Long userId);
}
