package tn.rostom.pi.services.IServices;

import java.util.List;

import tn.rostom.pi.entities.Appointment;

public interface IAppointmentService {
    Appointment addAppointment(Appointment appointment, Long userId, Long expertId);

    Appointment updateAppointment(Appointment appointment);

    boolean deleteAppointment(Long id);

    Appointment getAppointment(Long id);

    List<Appointment> getAllAppointments();

    List<Appointment> getAppointmentsByExpert(Long id);

    List<Appointment> getAppointmentsByUser(Long id);
}
