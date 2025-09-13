package ifsul.ads.hackathon.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ifsul.ads.hackathon.domain.dto.RemedioCadastroDTO;
import ifsul.ads.hackathon.domain.entity.Duracao;
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
        remedio.setFotoUrl(remedioDTO.getFotoUrl());
        remedio.setDosagem(remedioDTO.getDosagem());

        String strDuracao = remedioDTO.getDuracao();

        switch (strDuracao.toLowerCase()) {
            case "sempre":
                remedio.setDuracao(Duracao.SEMPRE);
                break;
            case "por":
                remedio.setDuracao(Duracao.POR);
                break;
            case "ate":
                remedio.setDuracao(Duracao.ATE);
                break;
            default:
                throw new IllegalArgumentException("Valor de duração inválido: " + strDuracao);
        }

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

        Usuario usuario = usuarioService.getUserByGoogleId(usuarioId);

        remedio.setUsuario(usuario);

        remedioRepository.save(remedio);

        System.out.println("-> Remédio cadastrado com sucesso! -> {" + remedio + "}");
    }

    public List<Remedio> listarRemediosPorUsuario(String googleId) {
        List<Remedio> remedios = remedioRepository.findByUsuarioGoogleId(googleId);
        System.out.println("-> Encontrados " + remedios.size() + " remédios para o usuário com Google ID: " + googleId + "}");
        return remedios;
    }

    public Remedio obterRemedioPorId(UUID remedioId) {
        return remedioRepository.findById(remedioId)
                .orElseThrow(() -> new IllegalArgumentException("Remédio não encontrado com ID: " + remedioId));
    }

    public void deletarRemedio(UUID remedioId) {
        Remedio remedio = obterRemedioPorId(remedioId);
        System.out.println("-> Deletando remédio: " + remedio);
        remedioRepository.delete(remedio);
    }

}
