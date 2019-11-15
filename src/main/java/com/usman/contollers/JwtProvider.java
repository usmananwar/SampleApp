package com.usman.contollers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtProvider {

	@Value("${app.service.jwt_secret:576d086ebdfdcb43991874869a7bd1d23f92386a0626666d65c4561ed73698a3}")
	private String jwtSecret;

	@Value("${app.service.jwt_ttl_millis:300000}") // default 5mins
	private int jwtTtl;

	public final static String AUTHORITIES_KEY = "authorities";

	public JwtProvider() {
	}

	public JwtProvider(String secret, int ttl) {
		this.jwtSecret = secret;
		this.jwtTtl = ttl;
	}

	public String generateToken(String userId, List<String> roles) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + jwtTtl);

		Claims claims = Jwts.claims().setSubject(userId).setIssuedAt(now).setExpiration(expiryDate);
		claims.put(AUTHORITIES_KEY, roles);

		String jwtToken = Jwts.builder().addClaims(claims).signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
		return jwtToken;
	}

	@SuppressWarnings("unchecked")
	public List<String> getGrantedAuthorities(String token) {
		Claims claims = getClaims(token);
		return (List<String>) claims.get(AUTHORITIES_KEY);
	}

	public String getUserIdFromJWT(String token) {
		String userId = null;
		try {
			Claims claims = getClaims(token);
			userId = claims.getSubject();
		} catch (ExpiredJwtException e) {
			//logger.warn("The has token  expired for id: " + e.getClaims().getSubject());
			userId = e.getClaims().getSubject();
		}
		return userId;
	}

	public Claims getClaims(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		return claims;
	}

	public boolean isValidToken(String authToken) {
		boolean result = false;
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			result = true;
		} catch (Exception ex) {
			// logger.error("Invalid JWT token");
			result = false;
		}
		return result;
	}

	public void validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
		} catch (MalformedJwtException ex) {
			throw new RuntimeException("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			throw new RuntimeException("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			throw new RuntimeException("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			throw new RuntimeException("JWT claims string is empty");
		} catch (Exception ex) {
			throw new RuntimeException("Invalid JWT signature");
		}
	}

}
