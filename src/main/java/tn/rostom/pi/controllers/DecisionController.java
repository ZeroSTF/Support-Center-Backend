package tn.rostom.pi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.rostom.pi.entities.Decision;
import tn.rostom.pi.services.IServices.IDecisionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/decision")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@Slf4j
public class DecisionController {
    private final IDecisionService decisionService;

    @PostMapping
    public ResponseEntity<Decision> createDecision(@RequestBody Decision decision,
            @RequestParam Long reclamationId,
            @RequestParam Long adminId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(decisionService.addDecision(decision, reclamationId, adminId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Decision> getDecisionById(@PathVariable Long id) {
        return ResponseEntity.ok(decisionService.getDecision(id));
    }

    @GetMapping("getDecisionByRecId/{reclamationId}")
    public ResponseEntity<Decision> getDecisionByRecId(@PathVariable Long reclamationId) {
        return ResponseEntity.ok(decisionService.getDecisionByRecId(reclamationId));
    }

    @GetMapping
    public ResponseEntity<List<Decision>> getAllDecisions() {
        return ResponseEntity.ok(decisionService.getDecisions());
    }

    @PutMapping("/update")
    public ResponseEntity<Decision> updateDecision(@RequestBody Decision decision,
                                                   @RequestParam Long reclamationId,
                                                   @RequestParam Long adminId) {
        return ResponseEntity.ok(decisionService.updateDecision(decision, reclamationId, adminId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDecision(@PathVariable Long id) {
        decisionService.deleteDecision(id);
        return ResponseEntity.noContent().build();
    }
}
