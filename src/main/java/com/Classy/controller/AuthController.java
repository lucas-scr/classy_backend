package com.Classy.controller;

import com.Classy.services.GoogleTokenVerifierService;
import com.Classy.services.JwtService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import org.springframework.web.bind.annotation.*;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {


    private GoogleIdTokenVerifier verifier;
    private final JwtService jwtService;
    private final GoogleTokenVerifierService googleTokenVerifierService;

    public AuthController (JwtService jwtService, GoogleTokenVerifierService googleTokenVerifierService){
        this.jwtService = jwtService;
        this.googleTokenVerifierService = googleTokenVerifierService;
    }

    @PostMapping("/google")
    public TokenResponse googleLogin(@RequestBody TokenRequest tokenRequest) throws GeneralSecurityException, java.io.IOException, Exception {
        var idToken = googleTokenVerifierService.verify(tokenRequest.getToken());
        if (idToken != null) {
            var payload = idToken.getPayload();
            String userId = payload.getSubject();
            String email = payload.getEmail();

            Map<String, Object> claims = new HashMap<>();
            claims.put("email", email);

            String jwt = jwtService.generateToken(userId, 60, claims);

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