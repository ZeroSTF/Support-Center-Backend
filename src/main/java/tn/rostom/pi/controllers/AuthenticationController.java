package tn.rostom.pi.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.rostom.pi.controllers.DTO.LoginDTO;
import tn.rostom.pi.controllers.DTO.LoginResponseDTO;
import tn.rostom.pi.controllers.DTO.LogoutResponseDTO;
import tn.rostom.pi.exceptions.InvalidCredentialsException;
import tn.rostom.pi.services.IServices.IAuthService;
import tn.rostom.pi.services.IServices.ITokenService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@Slf4j
public class AuthenticationController {
    private final IAuthService authService;
    private final ITokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO body) {
        try {
            LoginResponseDTO response = authService.login(body.getEmail(), body.getPassword());
            return ResponseEntity.ok(response);
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong code or password");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<LogoutResponseDTO> logout() {
        try {
            authService.logout();
            return ResponseEntity.ok(new LogoutResponseDTO(true, "Logout successful!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new LogoutResponseDTO(false, e.getMessage()));
        }
    }

    @GetMapping("/check-token")
    public ResponseEntity<Boolean> checkToken(@RequestHeader("AccessToken") String token) {
        try {
            if (tokenService.isTokenExpired(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
            } else {
                return ResponseEntity.ok(true);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

}