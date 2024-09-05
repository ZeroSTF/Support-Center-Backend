package tn.rostom.pi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.rostom.pi.entities.Appointment;
import tn.rostom.pi.services.IServices.IAppointmentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/appointment")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@Slf4j
public class AppointmentController {
    private final IAppointmentService appointmentService;
    
    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment,
                                                         @RequestParam Long userId,
                                                         @RequestParam Long expertId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(appointmentService.addAppointment(appointment, userId, expertId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.getAppointment(id));
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByUser(userId));
    }

    @GetMapping("/expert/{expertId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByExpert(@PathVariable Long expertId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByExpert(expertId));
    }

    @PutMapping("/update")
    public ResponseEntity<Appointment> updateAppointment(@RequestBody Appointment appointment) {
        return ResponseEntity.ok(appointmentService.updateAppointment(appointment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
