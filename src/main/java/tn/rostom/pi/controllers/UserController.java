package tn.rostom.pi.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.rostom.pi.controllers.DTO.UserDTO;
import tn.rostom.pi.entities.User;
import tn.rostom.pi.services.IServices.IUserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@Slf4j
public class UserController {
    private final IUserService userService;

    @GetMapping("/current")
    public ResponseEntity<?> getCurrent() {
        User currentUser=userService.getCurrentUser();
        String role = userService.getRoleString(currentUser);
        return ResponseEntity.ok().body(new UserDTO(currentUser.getId(), currentUser.getEmail(), currentUser.getName(), role));
    }

    @GetMapping("/currentDetails")
    public ResponseEntity<?> getCurrentDetails() {
        return ResponseEntity.ok().body(userService.getCurrentUser());
    }

}
