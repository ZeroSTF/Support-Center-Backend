package tn.rostom.pi.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.rostom.pi.controllers.DTO.LoginResponseDTO;
import tn.rostom.pi.entities.Role;
import tn.rostom.pi.entities.User;
import tn.rostom.pi.entities.enums.UStatus;
import tn.rostom.pi.exceptions.InvalidCredentialsException;
import tn.rostom.pi.repositories.UserRepository;
import tn.rostom.pi.services.IServices.IAuthService;
import tn.rostom.pi.services.IServices.ITokenService;
import java.util.Iterator;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService implements IAuthService {
    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    private final ITokenService tokenService;

    @Lazy
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponseDTO login(String email, String password) {
        try {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new InvalidCredentialsException("Wrong code or password"));

            if (!encoder.matches(password, user.getPassword()) || !user.getStatus().equals(UStatus.Active)) {
                throw new InvalidCredentialsException("Wrong code or password");
            }

            Authentication auth = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(email, password));
            String accessToken = tokenService.generateJwt(auth);
            String refreshToken = tokenService.generateRefreshToken(auth);

            String role = "user";
            Set<Role> roles = user.getRole();
            if (!roles.isEmpty()) {
                Iterator<Role> iterator = roles.iterator();
                Role firstRole = iterator.next();
                Long firstRoleId = firstRole.getId();
                if (firstRoleId == 1) {
                    role = "admin";
                }
                if (firstRoleId == 3) {
                    role = "old";
                }
            }

            return new LoginResponseDTO(user.getId(), user.getName(), user.getEmail(), role, accessToken, refreshToken);
        } catch (Exception e) {
            log.error("Error during login: ", e);
            throw new RuntimeException("Login failed", e);
        }
    }

    @Override
    public void logout() {
        SecurityContextHolder.clearContext();
    }

}
