package com.Classy.services;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ServiceGoogleTokenVerifier {

    private final GoogleIdTokenVerifier verifier;

    public ServiceGoogleTokenVerifier(@Value("${GOOGLE_CLIENT_ID}") String clientId){
        this.verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(),
                JacksonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList(clientId)).build();
    }


    public GoogleIdToken verify(String idTokenString) throws Exception {
        GoogleIdToken idToken = verifier.verify(idTokenString);
        if (idToken != null) {
            return verifier.verify(idTokenString);
        }
        throw new RuntimeException("ID Token inválido");
    }

}
