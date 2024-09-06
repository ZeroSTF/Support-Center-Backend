package tn.rostom.pi.controllers.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String email;
    private String name;
    private String role;
}
