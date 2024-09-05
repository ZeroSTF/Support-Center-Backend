package tn.rostom.pi.services.IServices;

import java.util.List;

import tn.rostom.pi.entities.Expert;

public interface IExpertService {
    Expert createExpert(Expert expert);

    Expert getExpertById(Long id);

    List<Expert> getAllExperts();

    List<Expert> getExpertsBySpecialization(String specialization);

    Expert updateExpert(Expert expert);

    void deleteExpert(Long id);
}
