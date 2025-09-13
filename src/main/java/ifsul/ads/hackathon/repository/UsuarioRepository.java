package ifsul.ads.hackathon.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ifsul.ads.hackathon.domain.entity.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    boolean existsByLogin(String login);
    
}
