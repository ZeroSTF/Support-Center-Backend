package tn.rostom.pi.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.web.bind.annotation.*;
import tn.rostom.pi.controllers.DTO.LoginDTO;
import tn.rostom.pi.controllers.DTO.LoginResponseDTO;
import tn.rostom.pi.controllers.DTO.LogoutResponseDTO;
import tn.rostom.pi.controllers.DTO.RegistrationDTO;
import tn.rostom.pi.controllers.DTO.UserDTO;
import tn.rostom.pi.entities.User;
import tn.rostom.pi.exceptions.InvalidCredentialsException;
import tn.rostom.pi.services.IServices.IAuthService;
import tn.rostom.pi.services.IServices.ITokenService;
import tn.rostom.pi.services.IServices.IUserService;

import org.springframework.security.core.userdetails.UserDetailsService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@Slf4j
public class AuthenticationController {
    private final IAuthService authService;
    private final ITokenService tokenService;
    private final UserDetailsService userDetailsService;
    private final IUserService userService;

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

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationDTO body) {
        User user = User.builder().name(body.getName()).email(body.getEmail()).password(body.getPassword()).build();
        User registeredUser = authService.registerUser(user);
        if (registeredUser != null) {
            return ResponseEntity.ok(registeredUser);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
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

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestHeader("RefreshToken") String refreshToken) {
        try {
            Jwt decodedRefreshToken = tokenService.decodeJwt(refreshToken);
            if (!"refresh".equals(decodedRefreshToken.getClaim("type"))) {
                throw new InvalidBearerTokenException("Invalid token type");
            }
            String username = decodedRefreshToken.getSubject();
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                    null, userDetails.getAuthorities());
            Map<String, String> tokens = tokenService.generateTokenPair(authentication);
            return ResponseEntity.ok(tokens);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }
    }

    @GetMapping("/current")
    public ResponseEntity<?> getCurrent() {
        User currentUser = userService.getCurrentUser();
        String role = userService.getRoleString(currentUser);
        return ResponseEntity.ok()
                .body(new UserDTO(currentUser.getId(), currentUser.getEmail(), currentUser.getName(), role));
    }

}