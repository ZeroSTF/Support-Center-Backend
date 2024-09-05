package tn.rostom.pi.controllers.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String role;
    private String jwt;
    private String refreshToken;
}