package com.Classy.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.Collections;
import java.util.Date;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Value("${GOOGLE_CLIENT_ID}")
    private String CLIENT_ID;


    @Value("${JWT_SECRET}")
    private String JWT_SECRET;

    private Key hmacKey;
    private GoogleIdTokenVerifier verifier;

    @PostConstruct
    public void init(){
        this.hmacKey = new SecretKeySpec(this.JWT_SECRET.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS256.getJcaName());
        this.verifier = new GoogleIdTokenVerifier.Builder(new com.google.api.client.http.javanet.NetHttpTransport(),
                new com.google.api.client.json.jackson2.JacksonFactory())
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();
    }


    @PostMapping("/google")
    public TokenResponse googleLogin(@RequestBody TokenRequest tokenRequest) throws GeneralSecurityException, java.io.IOException {
        GoogleIdToken idToken = verifier.verify(tokenRequest.getToken());
        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();

            String userId = payload.getSubject();
            String email = payload.getEmail();

            String jwt = Jwts.builder()
                    .setSubject(userId)
                    .claim("email", email)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hora
                    .signWith(hmacKey)
                    .compact();

            System.out.println(jwt);
            return new TokenResponse(jwt);

        } else {
            throw new GeneralSecurityException("Token inválido");
        }
    }

    public static class TokenRequest {
        private String token;

        public String getToken() { return token; }
        public void setToken(String token) { this.token = token; }
    }

    public static class TokenResponse {
        private String token;

        public TokenResponse(String token) { this.token = token; }
        public String getToken() { return token; }
        public void setToken(String token) { this.token = token; }
    }
}