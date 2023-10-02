package fa.com.mock_back_end.service.impl;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.http.ResponseCookie;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl {
    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	final Claims claims = extractAllClaims(token);
	return claimsResolver.apply(claims);
    }

    public String generateToken(String username) {
	Map<String, Object> claims = new HashMap<>();
	return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String username) {
	return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
		.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
	byte[] keyBytes = Decoders.BASE64.decode(SECRET);
	return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
	return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
	return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
	return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
	return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
	final String username = extractUsername(token);
	return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public ResponseCookie generatedJwtCookie(String jwtToken) {
	ResponseCookie cookie = ResponseCookie.from("jwtToken", jwtToken).maxAge(86400).httpOnly(true).build();
	return cookie;
    }

    public ResponseCookie cleanJwtCookie() {
	ResponseCookie cookie = ResponseCookie.from("jwtToken", null).build();
	return cookie;
    }
}
