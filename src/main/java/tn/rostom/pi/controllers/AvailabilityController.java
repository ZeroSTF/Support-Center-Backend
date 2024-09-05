package tn.rostom.pi.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.rostom.pi.entities.Availability;
import tn.rostom.pi.entities.Expert;
import tn.rostom.pi.services.IServices.IAvailabilityService;
import tn.rostom.pi.services.IServices.IExpertService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/availability")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@Slf4j
public class AvailabilityController {
    private final IAvailabilityService availabilityService;
    private final IExpertService expertService;

    @PostMapping
    public ResponseEntity<Availability> createAvailability(@RequestBody Availability availability) {
        return ResponseEntity.status(HttpStatus.CREATED).body(availabilityService.createAvailability(availability));
    }

    @GetMapping("/expert/{expertId}")
    public ResponseEntity<List<Availability>> getAvailabilitiesByExpert(@PathVariable Long expertId) {
        Expert expert = expertService.getExpertById(expertId);
        return ResponseEntity.ok(availabilityService.getAvailabilitiesByExpert(expert));
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> isExpertAvailable(@RequestParam Long expertId,
            @RequestParam LocalDateTime dateTime) {
        Expert expert = expertService.getExpertById(expertId);
        return ResponseEntity.ok(availabilityService.isExpertAvailable(expert, dateTime));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvailability(@PathVariable Long id) {
        availabilityService.deleteAvailability(id);
        return ResponseEntity.noContent().build();
    }
}
