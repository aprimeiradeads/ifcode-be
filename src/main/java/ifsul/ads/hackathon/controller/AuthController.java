package ifsul.ads.hackathon.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import ifsul.ads.hackathon.domain.dto.UsuarioCadastroDTO;
import ifsul.ads.hackathon.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Date;

@RestController
public class AuthController {

    @Value("${google.client.id}")
    private String googleClientId;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/api/auth/google")
    public ResponseEntity<?> authenticateGoogleUser(@RequestBody String idTokenString) {

        // Passo 1: Validar o ID Token do Google
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList(googleClientId))
                .build();

        try {
            GoogleIdToken idToken = verifier.verify(idTokenString);

            if (idToken == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token de ID do Google inválido.");
            }

            // Passo 2: Extrair os dados do usuário
            GoogleIdToken.Payload payload = idToken.getPayload();
            String googleId = payload.getSubject();
            String email = payload.getEmail();
            String name = (String) payload.get("name");

            // --- Lógica Simplificada de Usuário (substitua por acesso ao DB) ---
            // Simula a busca ou criação de um usuário.
            System.out.println("Usuário autenticado: " + email);

            // Passo 3: Gerar e retornar o seu próprio JWT para o front-end
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            String yourApiJwt = JWT.create()
                    .withSubject(googleId)
                    .withClaim("email", email)
                    .withClaim("name", name)
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 3600000)) // 1 hora de validade
                    .sign(algorithm);

            UsuarioCadastroDTO userDto = new UsuarioCadastroDTO(name, email, "defaultPassword", 00000000000L);
            usuarioService.salvarUsuario(userDto);
            return ResponseEntity.ok(Collections.singletonMap("token", yourApiJwt));

        } catch (GeneralSecurityException | IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro na validação do token.");
        }
    }
}