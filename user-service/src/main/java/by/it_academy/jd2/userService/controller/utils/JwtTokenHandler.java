package by.it_academy.jd2.userService.controller.utils;


import by.it_academy.jd2.userService.config.property.JWTProperty;
import by.it_academy.jd2.userService.dto.UserForTokenDTO;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtTokenHandler {

    private final JWTProperty property;


    public JwtTokenHandler(JWTProperty property) {
        this.property = property;
    }

    public String generateAccessToken(UserForTokenDTO user) {

        return Jwts.builder()
                .setSubject(user.getMail())
                .claim("role", user.getRole())
                .setIssuer(property.getIssuer())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(30)))
                .signWith(SignatureAlgorithm.HS512, property.getSecret())
                .compact();
    }

    public String getMail(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(property.getSecret())
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(property.getSecret()).parseClaimsJws(token);
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
