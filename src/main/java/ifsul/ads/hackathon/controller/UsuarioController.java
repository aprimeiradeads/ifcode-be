package ifsul.ads.hackathon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifsul.ads.hackathon.domain.entity.Usuario;
import ifsul.ads.hackathon.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/auth")
    public ResponseEntity<String> login(@RequestBody String idToken) {
        String jwt = usuarioService.login(idToken);
        return ResponseEntity.ok(jwt);
    }
}
