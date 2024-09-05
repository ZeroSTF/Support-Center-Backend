package tn.rostom.pi.services.IServices;

import java.time.LocalDateTime;
import java.util.List;

import tn.rostom.pi.entities.Availability;
import tn.rostom.pi.entities.Expert;

public interface IAvailabilityService {
    Availability createAvailability(Availability availability);

    List<Availability> getAvailabilitiesByExpert(Expert expert);

    boolean isExpertAvailable(Expert expert, LocalDateTime dateTime);

    void deleteAvailability(Long id);
}
