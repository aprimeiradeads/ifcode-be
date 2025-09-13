package ifsul.ads.hackathon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ifsul.ads.hackathon.domain.Usuario;
import ifsul.ads.hackathon.domain.dto.UsuarioCadastroDTO;
import ifsul.ads.hackathon.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void salvarUsuario(UsuarioCadastroDTO usuarioDTO) {
        
        if(usuarioRepository.existsByLogin(usuarioDTO.login())) {
            throw new IllegalArgumentException("Login já existe.");
        }
        
        Usuario usuario = new Usuario(
            usuarioDTO.nome(),
            usuarioDTO.login(),
            usuarioDTO.senha(),
            usuarioDTO.celular().toString()
        );
        usuarioRepository.save(usuario);
    }

}
