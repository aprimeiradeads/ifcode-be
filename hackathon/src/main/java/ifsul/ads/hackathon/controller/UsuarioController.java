package ifsul.ads.hackathon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifsul.ads.hackathon.domain.dto.UsuarioCadastroDTO;
import ifsul.ads.hackathon.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarUsuario(@RequestBody UsuarioCadastroDTO usuarioDTO) {
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

}
