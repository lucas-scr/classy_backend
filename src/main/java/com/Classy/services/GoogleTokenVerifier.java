package com.Classy.services;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;


import java.util.Collections;

@Service
public class GoogleTokenVerifier {

    @Value("${GOOGLE_CLIENT_ID}")
    private String CLIENT_ID ;

    private final GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
            new NetHttpTransport(),
            JacksonFactory.getDefaultInstance()
    ).setAudience(Collections.singletonList(CLIENT_ID)).build();

    public GoogleIdToken.Payload verify(String idTokenString) throws Exception {
        GoogleIdToken idToken = verifier.verify(idTokenString);
        if (idToken != null) {
            return idToken.getPayload();
        }
        throw new RuntimeException("ID Token inválido");
    }

}
