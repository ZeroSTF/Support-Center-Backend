package tn.rostom.pi.services;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import tn.rostom.pi.entities.Appointment;
import tn.rostom.pi.entities.Expert;
import tn.rostom.pi.entities.User;
import tn.rostom.pi.repositories.AppointmentRepository;
import tn.rostom.pi.repositories.ExpertRepository;
import tn.rostom.pi.repositories.UserRepository;
import tn.rostom.pi.services.IServices.IAppointmentService;

@Service
@RequiredArgsConstructor
public class AppointmentService implements IAppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final ExpertRepository expertRepository;
    private final AvailabilityService availabilityService;

    @Override
    public Appointment addAppointment(Appointment appointment, Long userId, Long expertId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        Expert expert = expertRepository.findById(expertId)
                .orElseThrow(() -> new EntityNotFoundException("Expert not found with id: " + expertId));

        if (!availabilityService.isExpertAvailable(expert, appointment.getDate())) {
            throw new IllegalStateException("Expert is not available at the requested time");
        }

        appointment.setUser(user);
        appointment.setExpert(expert);
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment updateAppointment(Appointment appointment, Long userId, Long expertId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        Expert expert = expertRepository.findById(expertId)
                .orElseThrow(() -> new EntityNotFoundException("Expert not found with id: " + expertId));
        if (!availabilityService.isExpertAvailable(expert, appointment.getDate())) {
            throw new IllegalStateException("Expert is not available at the requested time");
        }
        appointment.setUser(user);
        appointment.setExpert(expert);
        return appointmentRepository.save(appointment);
    }

    @Override
    public boolean deleteAppointment(Long id) {
        if (!appointmentRepository.existsById(id))
            return false;
        appointmentRepository.deleteById(id);
        return true;
    }

    @Override
    public Appointment getAppointment(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> getAppointmentsByExpert(Long id) {
        return appointmentRepository.findByExpertId(id);
    }

    @Override
    public List<Appointment> getAppointmentsByUser(Long id) {
        return appointmentRepository.findByUserId(id);
    }

}
