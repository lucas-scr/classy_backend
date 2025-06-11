package com.Classy.controller;

import com.Classy.DTO.TokenRequest;
import com.Classy.DTO.TokenResponse;
import com.Classy.DTO.UsuarioDTO;
import com.Classy.services.ServiceGoogleTokenVerifier;
import com.Classy.services.ServiceJWT;
import com.Classy.services.ServiceUsuario;
import com.Classy.util.ProvedorAutenticacao;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import org.springframework.web.bind.annotation.*;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {


    private GoogleIdTokenVerifier verifier;
    private final ServiceJWT serviceJWT;
    private final ServiceUsuario serviceUsuario;
    private final ServiceGoogleTokenVerifier serviceGoogleTokenVerifier;

    public AuthController (ServiceJWT serviceJWT, ServiceGoogleTokenVerifier serviceGoogleTokenVerifier, ServiceUsuario serviceUsuario){
        this.serviceJWT = serviceJWT;
        this.serviceGoogleTokenVerifier = serviceGoogleTokenVerifier;
        this.serviceUsuario = serviceUsuario;
    }

    @PostMapping("/google")
    public TokenResponse googleLogin(@RequestBody TokenRequest tokenRequest) throws GeneralSecurityException, java.io.IOException, Exception {
        var idToken = serviceGoogleTokenVerifier.verify(tokenRequest.getToken());
        if (idToken != null) {
            var payload = idToken.getPayload();
            String userId = payload.getSubject();

            UsuarioDTO usuarioLogado = new UsuarioDTO();
            usuarioLogado.setEmail(payload.getEmail());
            usuarioLogado.setNome((String) payload.get("name"));
            usuarioLogado.setProvedor(ProvedorAutenticacao.GOOGLE);

            Map<String, Object> claims = new HashMap<>();
            claims.put("email", usuarioLogado.getEmail());
            claims.put("roles", List.of("ROLE_USER"));
            String jwt = serviceJWT.gerarToken(userId, 15, claims);
            System.out.println(usuarioLogado.getEmail() + " usuario");

            if(!serviceUsuario.verificarExistenciaUsuario(usuarioLogado.getEmail())){
                serviceUsuario.cadastrarUsuarioGoogle(usuarioLogado);
            }

            System.out.println(jwt);
            return new TokenResponse(jwt);
        }
        throw new GeneralSecurityException("Token inválido");
    }

}