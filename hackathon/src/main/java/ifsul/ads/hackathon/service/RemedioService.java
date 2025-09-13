package ifsul.ads.hackathon.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ifsul.ads.hackathon.domain.dto.RemedioCadastroDTO;
import ifsul.ads.hackathon.domain.entity.Remedio;
import ifsul.ads.hackathon.domain.entity.Repeticao;
import ifsul.ads.hackathon.domain.entity.Usuario;
import ifsul.ads.hackathon.repository.RemedioRepository;

@Service
public class RemedioService {

    @Autowired
    private RemedioRepository remedioRepository;

    @Autowired
    private UsuarioService usuarioService;

    public void cadastrarRemedio(RemedioCadastroDTO remedioDTO) {
        
        Remedio remedio = new Remedio();
        UUID id = UUID.randomUUID();
        remedio.setId(id);
        remedio.setNome(remedioDTO.nome());
        remedio.setDescricao(remedioDTO.descricao());
        remedio.setFotoUrl(remedioDTO.fotoUrl());
        remedio.setDosagem(remedioDTO.dosagem());

        String strRepeticao = remedioDTO.repeticao();

        switch (strRepeticao.toLowerCase()) {
            case "diario":
                remedio.setRepeticao(Repeticao.DIARIO);
                break;
            case "semanal":
                remedio.setRepeticao(Repeticao.SEMANAL);
                break;
            case "mensal":
                remedio.setRepeticao(Repeticao.MENSAL);
                break;
            default:
                throw new IllegalArgumentException("Valor de repetição inválido: " + strRepeticao);
        }

        remedio.setRepeticaoDias(remedioDTO.repeticaoDias());

        remedio.setRepeticaoSemana(remedioDTO.repeticaoSemana());

        Usuario usuario = usuarioService.obterUsuarioPorId(remedioDTO.usuarioId());

        remedio.setUsuario(usuario);
        
        remedioRepository.save(remedio);
    }

    

}
