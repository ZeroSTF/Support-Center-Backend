package tn.rostom.pi.services;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import tn.rostom.pi.entities.Expert;
import tn.rostom.pi.repositories.ExpertRepository;
import tn.rostom.pi.services.IServices.IExpertService;

@Service
@RequiredArgsConstructor
public class ExpertService implements IExpertService {
    private final ExpertRepository expertRepository;

    @Override
    public Expert createExpert(Expert expert) {
        return expertRepository.save(expert);
    }

    @Override
    public Expert getExpertById(Long id) {
        return expertRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expert not found with id: " + id));
    }

    @Override
    public List<Expert> getAllExperts() {
        return expertRepository.findAll();
    }

    @Override
    public List<Expert> getExpertsBySpecialization(String specialization) {
        return expertRepository.findBySpecialization(specialization);
    }

    @Override
    public Expert updateExpert(Expert expert) {
        return expertRepository.save(expert);
    }

    @Override
    public void deleteExpert(Long id) {
        expertRepository.deleteById(id);
    }

}
