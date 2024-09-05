package tn.rostom.pi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.rostom.pi.entities.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByExpertId(Long id);
    List<Appointment> findByUserId(Long id);
}
