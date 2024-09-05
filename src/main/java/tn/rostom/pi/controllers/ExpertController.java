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
import tn.rostom.pi.entities.Expert;
import tn.rostom.pi.services.IServices.IExpertService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/expert")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@Slf4j
public class ExpertController {
    private final IExpertService expertService;

    @PostMapping
    public ResponseEntity<Expert> createExpert(@RequestBody Expert expert) {
        return ResponseEntity.status(HttpStatus.CREATED).body(expertService.createExpert(expert));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expert> getExpertById(@PathVariable Long id) {
        return ResponseEntity.ok(expertService.getExpertById(id));
    }

    @GetMapping
    public ResponseEntity<List<Expert>> getAllExperts() {
        return ResponseEntity.ok(expertService.getAllExperts());
    }

    @GetMapping("/specialization")
    public ResponseEntity<List<Expert>> getExpertsBySpecialization(@RequestParam String specialization) {
        return ResponseEntity.ok(expertService.getExpertsBySpecialization(specialization));
    }

    @PutMapping("/update")
    public ResponseEntity<Expert> updateExpert(@RequestBody Expert expert) {
        return ResponseEntity.ok(expertService.updateExpert(expert));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpert(@PathVariable Long id) {
        expertService.deleteExpert(id);
        return ResponseEntity.noContent().build();
    }
}
