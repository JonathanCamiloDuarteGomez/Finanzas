package com.Davivienda.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

// ⚠ Puedes mover esto a application.properties luego
private final String SECRET = "n8P3Lx92jWQ4s7F1zkAx89JkLmR82qaPp2kQ3wLmZcVqTtHsQe9BmPp1FsHk7DyQ";
private final long EXPIRATION_MS = 1000 * 60 * 60 * 4; // 4 horas

private Key getSigningKey() {
	return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
}

public String generateToken(String username) {
	Date ahora = new Date();
	Date expiracion = new Date(ahora.getTime() + EXPIRATION_MS);
	
	return Jwts.builder()
			       .setSubject(username)
			       .setIssuedAt(ahora)
			       .setExpiration(expiracion)
			       .signWith(getSigningKey(), SignatureAlgorithm.HS256)
			       .compact();
}

public String getUsernameFromToken(String token) {
	return parseClaims(token).getSubject();
}

public boolean isTokenValid(String token) {
	try {
		parseClaims(token);
		return true;
	} catch (JwtException | IllegalArgumentException e) {
		return false;
	}
}

private Claims parseClaims(String token) {
	return Jwts.parserBuilder()
			       .setSigningKey(getSigningKey())
			       .build()
			       .parseClaimsJws(token)
			       .getBody();
}
}
