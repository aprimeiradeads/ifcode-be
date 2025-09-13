package ifsul.ads.hackathon.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ifsul.ads.hackathon.domain.entity.Usuario;
import ifsul.ads.hackathon.repository.UsuarioRepository;
import jakarta.validation.Valid;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void salvarUsuario(@Valid Usuario user) {
        
        if(usuarioRepository.existsByLogin(user.getLogin())) {
            throw new IllegalArgumentException("Login já existe.");
        }

        if(user.getCelular() == null || user.getCelular().toString().length() != 11){
            throw new IllegalArgumentException("Número de celular inválido.");
        }

        if(user.getLogin() == null || user.getLogin().length() < 3) {
            throw new IllegalArgumentException("Login não pode ser menor que 3 caracteres.");
        }

        if(user.getSenha() == null || user.getSenha().length() < 3) {
            throw new IllegalArgumentException("Senha deve ter pelo menos 3 caracteres.");
        }

        if(user.getNome() == null || user.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        
        Usuario usuario = new Usuario(
            user.getId(),
            user.getNome(),
            user.getLogin(),
            user.getSenha(),
            user.getCelular().toString()
        );
        usuarioRepository.save(usuario);
    }

    public Usuario obterUsuarioPorId(String usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + usuarioId));
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
}
