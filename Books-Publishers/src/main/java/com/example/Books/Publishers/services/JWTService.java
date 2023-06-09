package com.example.Books.Publishers.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * Core JWT Services such as generating JWT token based on username,
 * Getting username,
 * Validating token.
 */
@Service
@RequiredArgsConstructor
public class JWTService {

    final private SecretKey key=Keys.hmacShaKeyFor("Keys.hmacShaKeyFor(\"1234567890\".getBytes(StandardCharsets.UTF_8));".getBytes(StandardCharsets.UTF_8));;
    final private JwtParser parser=Jwts.parser().setSigningKey(key);

    public String generate(String userName) {
        JwtBuilder builder = Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(15, ChronoUnit.DAYS)))
                .signWith(key);


        return builder.compact();

    }

    public String getUserID(String token) {
        //Subject is where we store the ID
        var a = parser
                .parseClaimsJws(token)
                .getBody().getSubject();
        return a;


    }

    public boolean isValid(String token, String userName) {
        Claims claims = parser
                .parseClaimsJws(token).
                getBody();
        boolean unexpired = claims.getExpiration().after(Date.from(Instant.now()));


        return unexpired && userName.equalsIgnoreCase(claims.getSubject());
    }
}