package com.Classy.controller;

import com.Classy.DTO.TokenRequest;
import com.Classy.DTO.TokenResponse;
import com.Classy.DTO.UsuarioDTO;
import com.Classy.services.GoogleTokenVerifierService;
import com.Classy.services.JwtService;
import com.Classy.services.UsuarioService;
import com.Classy.util.ProvedorAutenticacao;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final JwtService jwtService;
    private final UsuarioService usuarioService;
    private final GoogleTokenVerifierService googleTokenVerifierService;

    public AuthController (JwtService jwtService, GoogleTokenVerifierService googleTokenVerifierService, UsuarioService usuarioService){
        this.jwtService = jwtService;
        this.googleTokenVerifierService = googleTokenVerifierService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/google")
    public TokenResponse googleLogin(@RequestBody TokenRequest tokenRequest) throws GeneralSecurityException, java.io.IOException, Exception {
        var idToken = googleTokenVerifierService.verify(tokenRequest.getToken());
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
            String jwt = jwtService.gerarToken(userId, 60, claims);
            System.out.println(usuarioLogado.getEmail() + " usuario");

            if(!usuarioService.verificarExistenciaUsuario(usuarioLogado.getEmail())){
                usuarioService.cadastrarUsuarioGoogle(usuarioLogado);
            }

            System.out.println(jwt);
            return new TokenResponse(jwt);
        }
        throw new GeneralSecurityException("Token inválido");
    }

}