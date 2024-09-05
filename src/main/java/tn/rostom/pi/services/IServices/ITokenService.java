package tn.rostom.pi.services.IServices;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;

public interface ITokenService {
    String generateJwt(Authentication auth);

    String generateRefreshToken(Authentication auth);

    Jwt decodeJwt(String token);

    Boolean isTokenExpired(String token);

    Map<String, String> generateTokenPair(Authentication auth);

}
