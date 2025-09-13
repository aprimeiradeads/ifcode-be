package ifsul.ads.hackathon.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ifsul.ads.hackathon.domain.entity.Remedio;

@Repository
public interface RemedioRepository extends JpaRepository<Remedio, UUID> {
    
    Remedio findByNomeComercial(String nomeComercial);

    Remedio findByNomeApelido(String nomeApelido);
    
    Remedio findByDescricao(String descricao);
    
    
}
