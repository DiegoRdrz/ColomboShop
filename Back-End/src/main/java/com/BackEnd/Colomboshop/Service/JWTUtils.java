package com.BackEnd.Colomboshop.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;

@Component
public class JWTUtils {
    private SecretKey key;
    private static final long  EXPIRATION_TIME = 86400000;

    public JWTUtils(){
        String secretString = "vpbSUJx88tRRuetN7k9YdNR2hhb65q6P6E6K69h8D27VeRB68wq3GnVYE9k69pZqF67Cm38ykSp48ur374WN8we3CEfHxnNm36BG";
        byte[] keyBytes = Base64.getDecoder().decode(secretString.getBytes(StandardCharsets.UTF_8));
        this.key = new SecretKeySpec(keyBytes,"hmacSHA256");
    }

    public  String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public  String generateRefreshToken(HashMap<String, Object> claims, UserDetails userDetails){
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public String extractUserName(String token){
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction){
        return claimsTFunction.apply(Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload());
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())&&!isTokenExpired(token));

    }

    public boolean isTokenExpired(String token){
        return  extractClaims(token, Claims::getExpiration).before(new Date());
    }
}
