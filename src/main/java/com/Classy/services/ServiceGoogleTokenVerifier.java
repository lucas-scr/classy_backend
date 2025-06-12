package com.Classy.services;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class ServiceGoogleTokenVerifier {

    private final GoogleIdTokenVerifier verifier;

    public ServiceGoogleTokenVerifier(@Value("${GOOGLE_CLIENT_ID}") String clientId){
        if (clientId == null || clientId.isBlank()) {
            throw new IllegalStateException("GOOGLE_CLIENT_ID não está definido!");
        }
        System.out.println("GOOGLE_CLIENT_ID carregado: " + clientId);
        this.verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(),
                JacksonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList(clientId)).build();
    }


    public GoogleIdToken verify(String idTokenString) throws Exception {
        try{
            GoogleIdToken idToken = verifier.verify(idTokenString);
            if (idToken == null) {
                throw new IllegalArgumentException("ID token inválido ou expirado.");
            }
            throw new RuntimeException("ID Token inválido");
        }catch (GeneralSecurityException | IOException e){
            throw new RuntimeException("Erro ao verificar ID Token: " + e.getMessage(), e);
        }
    }

}
