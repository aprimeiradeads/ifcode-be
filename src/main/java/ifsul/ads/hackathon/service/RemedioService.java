package ifsul.ads.hackathon.service;

import java.util.List;
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

    public void cadastrarRemedio(RemedioCadastroDTO remedioDTO, String usuarioId) {
        
        Remedio remedio = new Remedio();
        UUID id = UUID.randomUUID();
        remedio.setId(id);
        remedio.setNome(remedioDTO.getNome());
        remedio.setDescricao(remedioDTO.getDescricao());
        remedio.setFotoUrl("");
        remedio.setDosagem(remedioDTO.getDosagem());

        String strRepeticao = remedioDTO.getRepeticao();

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

        remedio.setRepeticaoDias(0);

        remedio.setRepeticaoSemana("");

        Usuario usuario = usuarioService.obterUsuarioPorId(usuarioId);

        remedio.setUsuario(usuario);
        
        remedioRepository.save(remedio);
    }

    public List<Remedio> listarRemediosPorUsuario(String usuarioId) {
        return remedioRepository.findByUsuarioId(usuarioId);
    }

    public Remedio obterRemedioPorId(String usuarioId,UUID remedioId) {
        return remedioRepository.findById(remedioId)
                .filter(remedio -> remedio.getUsuario().getId().equals(usuarioId))
                .orElseThrow(() -> new IllegalArgumentException("Remédio não encontrado com ID: " + remedioId));
    }

    

}
