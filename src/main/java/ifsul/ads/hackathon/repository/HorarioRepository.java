package ifsul.ads.hackathon.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ifsul.ads.hackathon.domain.entity.Horario;

public interface HorarioRepository extends JpaRepository<Horario, UUID> {

}
