package tn.rostom.pi.services;

import java.util.Iterator;
import java.util.Set;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import tn.rostom.pi.entities.Role;
import tn.rostom.pi.entities.User;
import tn.rostom.pi.repositories.UserRepository;
import tn.rostom.pi.services.IServices.IUserService;
import org.springframework.security.core.Authentication;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;

    @Override
    public User getCurrentUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            return userRepository.findByEmail(email)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
        } catch (Exception e) {
            throw new RuntimeException("Failed to get current user", e);

        }
    }

    @Override
    public String getRoleString(User user) {
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
        return role;
    }

}
