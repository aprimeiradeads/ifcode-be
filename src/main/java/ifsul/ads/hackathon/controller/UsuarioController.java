package ifsul.ads.hackathon.controller;

import java.util.List;
<<<<<<< HEAD
=======
import java.util.UUID;
>>>>>>> 30631b9c40363e2cfc8189a40a4bbb2d58012f78

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifsul.ads.hackathon.domain.dto.UsuarioCadastroDTO;
import ifsul.ads.hackathon.domain.entity.Usuario;
import ifsul.ads.hackathon.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarUsuario(@Valid @RequestBody UsuarioCadastroDTO usuarioDTO) {
        System.out.println("Cadastrando usuário: " + usuarioDTO);
        
        try {
            usuarioService.salvarUsuario(usuarioDTO);
            System.out.println("Usuário cadastrado com sucesso!");
            return ResponseEntity.ok("Usuário cadastrado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar usuário: " + e.getMessage());
            return ResponseEntity.badRequest().body("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        System.out.println("Listando usuários...");

        try {
            List<Usuario> usuarios = usuarioService.listarUsuarios();
            System.out.println("Usuários listados com sucesso!");
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

}
