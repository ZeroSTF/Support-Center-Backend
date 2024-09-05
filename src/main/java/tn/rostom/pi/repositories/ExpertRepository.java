package tn.rostom.pi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.rostom.pi.entities.Expert;

public interface ExpertRepository extends JpaRepository<Expert, Long> {
    List<Expert> findBySpecialization(String specialization);
}
