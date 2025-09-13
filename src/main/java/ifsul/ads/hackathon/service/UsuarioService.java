package ifsul.ads.hackathon.service;

import java.util.List;
import java.util.UUID;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ifsul.ads.hackathon.domain.dto.UsuarioCadastroDTO;
import ifsul.ads.hackathon.domain.entity.Usuario;
import ifsul.ads.hackathon.repository.UsuarioRepository;
import jakarta.validation.Valid;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void salvarUsuario(@Valid UsuarioCadastroDTO usuarioDTO) {
        
        if(usuarioRepository.existsByLogin(usuarioDTO.login())) {
            throw new IllegalArgumentException("Login já existe.");
        }

        if(usuarioDTO.celular() == null || usuarioDTO.celular().toString().length() != 11){
            throw new IllegalArgumentException("Número de celular inválido.");
        }

        if(usuarioDTO.login() == null || usuarioDTO.login().length() < 3) {
            throw new IllegalArgumentException("Login não pode ser menor que 3 caracteres.");
        }

        if(usuarioDTO.senha() == null || usuarioDTO.senha().length() < 3) {
            throw new IllegalArgumentException("Senha deve ter pelo menos 3 caracteres.");
        }

        if(usuarioDTO.nome() == null || usuarioDTO.nome().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        
        Usuario usuario = new Usuario(
            usuarioDTO.nome(),
            usuarioDTO.login(),
            usuarioDTO.senha(),
            usuarioDTO.celular().toString()
        );
        usuarioRepository.save(usuario);
    }

    public Usuario obterUsuarioPorId(UUID usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + usuarioId));
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
}
