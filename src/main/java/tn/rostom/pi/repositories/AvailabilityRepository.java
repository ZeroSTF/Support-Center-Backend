package tn.rostom.pi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.rostom.pi.entities.Availability;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    List<Availability> findByExpertId(Long id);
}
