package ifsul.ads.hackathon.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ifsul.ads.hackathon.domain.entity.Remedio;

@Repository
public interface RemedioRepository extends JpaRepository<Remedio, UUID> {

    List<Remedio> findByUsuarioGoogleId(String usuarioGoogleId);
    
}
