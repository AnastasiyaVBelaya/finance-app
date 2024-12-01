package by.it_academy.jd2.classifierService.controller.utils;

import by.it_academy.jd2.classifierService.config.property.JWTProperty;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenHandler {

    private final JWTProperty property;

    public JwtTokenHandler(JWTProperty property) {
        this.property = property;
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(property.getSecret())
                .parseClaimsJws(token)
                .getBody();
    }

    public String getMail(String token) {
        return getClaims(token).getSubject();
    }

    public String getRole(String token) {
        return getClaims(token).get("role", String.class);
    }

    public boolean validate(String token) {
        try {
            getClaims(token);
            return true;
        } catch (SignatureException ex) {
            throw new IllegalArgumentException("Неверная подпись JWT токена", ex);
        } catch (MalformedJwtException ex) {
            throw new IllegalArgumentException("Неверный формат JWT токена", ex);
        } catch (ExpiredJwtException ex) {
            throw new IllegalArgumentException("JWT токен истек", ex);
        } catch (UnsupportedJwtException ex) {
            throw new IllegalArgumentException("Не поддерживаемый JWT токен", ex);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Пустые или некорректные данные JWT токена", ex);
        }
    }
}