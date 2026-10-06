package io.rahulnanhore.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.rahulnanhore.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import static java.time.Instant.now;

@Service
public class    JwtProvider {

    private final SecretKey key;
    private final String issuer;
    private final long expiration;

    public JwtProvider(
            @Value("${security.jwt.secret}") String secret,
            @Value("${security.jwt.issuer}") String issuer,
            @Value("${security.jwt.expiration}") long expiration) {

        if (secret == null || secret.length() < 64) {
            throw new IllegalArgumentException("Invalid secret");
        }

        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.issuer = issuer;
        this.expiration = expiration;
    }

    public String generateToken(User user) {

        String jwt = Jwts.builder()
                .subject(Long.toString(user.getId()))
                .issuer(issuer)
                .issuedAt(Date.from(now()))
                .expiration(Date.from(now().plusSeconds(expiration)))
                .claim("role", user.getRole().name())
                .signWith(key, Jwts.SIG.HS512)
                .compact();
        return jwt;
    }

}
