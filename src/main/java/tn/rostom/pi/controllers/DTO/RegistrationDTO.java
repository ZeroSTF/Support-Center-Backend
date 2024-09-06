package tn.rostom.pi.controllers.DTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationDTO {
    String name;
    String email;
    String password;
}
