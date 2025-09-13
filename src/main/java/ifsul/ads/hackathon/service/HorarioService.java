package ifsul.ads.hackathon.service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ifsul.ads.hackathon.domain.entity.Horario;
import ifsul.ads.hackathon.domain.entity.Remedio;
import ifsul.ads.hackathon.repository.HorarioRepository;

@Service
public class HorarioService {
    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private RemedioService remedioService;

    public void cadastrarHorario(UUID remedioId, String horario) {
        LocalTime localTime = LocalTime.parse(horario, DateTimeFormatter.ofPattern("HH:mm"));
        Remedio remedio = remedioService.obterRemedioPorId(remedioId);

        horarioRepository.save(new Horario(remedio, localTime));
    }
}
