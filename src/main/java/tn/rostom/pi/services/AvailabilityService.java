package tn.rostom.pi.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import tn.rostom.pi.entities.Availability;
import tn.rostom.pi.entities.Expert;
import tn.rostom.pi.repositories.AvailabilityRepository;
import tn.rostom.pi.services.IServices.IAvailabilityService;

@Service
@RequiredArgsConstructor
public class AvailabilityService implements IAvailabilityService {
    private final AvailabilityRepository availabilityRepository;

    @Override
    public Availability createAvailability(Availability availability) {
        return availabilityRepository.save(availability);
    }

    @Override
    public List<Availability> getAvailabilitiesByExpert(Expert expert) {
        return availabilityRepository.findByExpertId(expert.getId());
    }

    @Override
    public boolean isExpertAvailable(Expert expert, LocalDateTime dateTime) {
        List<Availability> availabilities = getAvailabilitiesByExpert(expert);
        return availabilities.stream()
            .anyMatch(a -> dateTime.isAfter(a.getStartTime()) && dateTime.isBefore(a.getEndTime()));
    }

    @Override
    public void deleteAvailability(Long id) {
        Availability availability = availabilityRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Availability not found with id: " + id));
        availabilityRepository.delete(availability);
    }

}
