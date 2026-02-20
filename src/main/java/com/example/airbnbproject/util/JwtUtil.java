package com.example.airbnbproject.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;
@Component
public class JwtUtil {
    private static final String SECRET = "mySecretKeymySecretKeymySecretKey12";
    private final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    // Generate Token (username + role)
    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role","ROLE_" + role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }
    public String extractRole(String token) {
        return getClaims(token).get("role", String.class);
    }
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    // Ye method validateToken ka error khatam kar dega
    public Boolean validateToken(String token, org.springframework.security.core.userdetails.UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    // Token expiry check karne ke liye helper method
    private Boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new java.util.Date());
    }
}
