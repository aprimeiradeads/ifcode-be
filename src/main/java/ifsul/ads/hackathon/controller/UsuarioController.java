package ifsul.ads.hackathon.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifsul.ads.hackathon.service.UsuarioService;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/auth")
    public ResponseEntity<?> login(@RequestBody String idToken) {
        String jwt = usuarioService.login(idToken);
        
        return ResponseEntity.ok(Collections.singletonMap("token", jwt));
    }
}
