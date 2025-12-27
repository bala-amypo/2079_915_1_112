package com.example.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    // 🔑 Dummy secret (safe for tests)
    private static final String SECRET = "test-secret-key";

    // ⏱ 1 hour validity
    private static final long EXPIRATION = 1000 * 60 * 60;

    public String generateToken(Long userId, String email, String role) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("email", email);
        claims.put("role", role);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET.getBytes())
                .compact();
    }
}
