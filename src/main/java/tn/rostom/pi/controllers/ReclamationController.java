package tn.rostom.pi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.rostom.pi.entities.Reclamation;
import tn.rostom.pi.services.IServices.IReclamationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reclamation")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@Slf4j
public class ReclamationController {
    private final IReclamationService reclamationService;

    @GetMapping
    public ResponseEntity<List<Reclamation>> getAllReclamations() {
        return ResponseEntity.ok(reclamationService.getAllReclamations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reclamation> getReclamationById(@PathVariable Long id) {
        return ResponseEntity.ok(reclamationService.getReclamationById(id));
    }

    @PostMapping
    public ResponseEntity<Reclamation> createReclamation(@RequestBody Reclamation reclamation,
            @RequestParam Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reclamationService.createReclamation(reclamation, userId));
    }

    @PutMapping("/update")
    public ResponseEntity<Reclamation> updateReclamation(@RequestBody Reclamation reclamation) {
        return ResponseEntity.ok(reclamationService.updateReclamation(reclamation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReclamation(@PathVariable Long id) {
        reclamationService.deleteReclamation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reclamation>> getReclamationsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(reclamationService.getReclamationsByUserId(userId));
    }

}
