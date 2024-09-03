package tn.rostom.pi.services.IServices;

import tn.rostom.pi.controllers.DTO.LoginResponseDTO;
import tn.rostom.pi.entities.User;

public interface IAuthService {
    LoginResponseDTO login(String email, String password);
    void logout();
}
