package io.rahulnanhore.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    private final SecretKey key;
    private final String issuer;

    public JwtUtil(
            @Value("${security.jwt.secret}") String secret,
            @Value("${security.jwt.issuer}") String issuer
    ) {
        if (secret == null || secret.length() < 64) {
            throw new IllegalArgumentException("Invalid secret");
        }
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.issuer = issuer;
    }

    public Claims extractClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(key)
                .requireIssuer(issuer)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractEmail(String token) {
        return extractClaims(token).get("email").toString();
    }

    public String extractRole(String token) {
        return extractClaims(token).get("role").toString();
    }

    public String extractId(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean isValid(String token) {
        return extractClaims(token).getExpiration().after(new Date());
    }
}
