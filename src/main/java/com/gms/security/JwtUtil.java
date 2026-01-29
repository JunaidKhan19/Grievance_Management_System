package com.gms.security;

import com.gms.enums.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret; // Secret key (keep in env for production)

    @Value("${jwt.expirationMs}")
    private int jwtExpirationMs; // Token expiration in milliseconds

    // Generate signing key from secret
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    /**
     * Generate a JWT token containing actorId and actorRole
     *
     * @param actorId   Unique ID of the logged-in actor
     * @param actorRole Role of the actor (ADMIN, OFFICER, EMPLOYEE)
     * @return JWT token string
     */
    public String generateToken(String actorId, Role role) {
        return Jwts.builder()
                .setSubject(actorId)
                .claim("actorRole", role.name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Extract claims from JWT token
     *
     * @param token JWT string
     * @return Claims object
     * @throws JwtException if invalid
     */
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Validate JWT token
     *
     * @param token JWT string
     * @return true if valid, false otherwise
     */
    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Convenience method: extract actorId
     */
    public String getActorId(String token) {
        return getClaims(token).getSubject();
    }

    /**
     * Convenience method: extract actorRole
     */
    public String getActorRole(String token) {
        return getClaims(token).get("actorRole", String.class);
    }

    /**
     * Convenience method: extract actorRole
     */
    public Role getRoleFromToken(String token) {
        String roleStr = getClaims(token).get("actorRole", String.class);
        try {
            return Role.valueOf(roleStr);
        } catch (IllegalArgumentException ex) {
            return null; // invalid / tampered token
        }
    }

}
