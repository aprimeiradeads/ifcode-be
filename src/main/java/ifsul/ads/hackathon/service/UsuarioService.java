package ifsul.ads.hackathon.service;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import ifsul.ads.hackathon.domain.entity.Usuario;
import ifsul.ads.hackathon.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Value("${google.client.id}")
    private String googleClientId;

    @Value("${google.client.secret}")
    private String googleClientSecret;

    public String login(String idTokenString) {
        try {

            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(),
                    JacksonFactory.getDefaultInstance())
                    .setAudience(Collections.singletonList(googleClientId))
                    .build();

            GoogleIdToken idToken = verifier.verify(idTokenString);

            if (idToken == null) {
                throw new IllegalArgumentException("Token de autenticação inválido.");
            }

            GoogleIdToken.Payload payload = idToken.getPayload();
            String googleId = payload.getSubject();
            String email = payload.getEmail();
            String name = (String) payload.get("name");

            createOrGetUser(googleId, email, name);

            Algorithm algorithm = Algorithm.HMAC256(googleClientSecret);
            String ownApiJwt = JWT.create()
                    .withSubject(googleId)
                    .withClaim("email", email)
                    .withClaim("name", name)
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 3600000)) // 1 hora de validade
                    .sign(algorithm);

            return ownApiJwt;
        } catch (Exception e) {
            throw new IllegalArgumentException("Erro ao processar a autenticação: " + e.getMessage());
        }
    }

    private Usuario createOrGetUser(String googleId, String email, String name) {
        return usuarioRepository.findByGoogleId(googleId).orElseGet(() -> {
            return usuarioRepository.save(new Usuario(googleId, email, name));
        });
    }

    public Usuario getUserByGoogleId(String googleId) {
        return usuarioRepository.findByGoogleId(googleId).orElseThrow();
    }
}
