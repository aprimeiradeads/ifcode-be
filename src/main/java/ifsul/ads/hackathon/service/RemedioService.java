package ifsul.ads.hackathon.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.util.DateTime;

import ifsul.ads.hackathon.domain.dto.RemedioCadastroDTO;
import ifsul.ads.hackathon.domain.entity.Duracao;
import ifsul.ads.hackathon.domain.entity.Horario;
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

    @Autowired
    private HorarioService horarioService;

    public void cadastrarRemedio(RemedioCadastroDTO remedioDTO, String usuarioId) {
        Remedio remedio = new Remedio();
        remedio.setId(UUID.randomUUID());
        remedio.setNome(remedioDTO.getNome());
        remedio.setDescricao(remedioDTO.getDescricao());
        remedio.setFotoUrl(remedioDTO.getFotoUrl());
        remedio.setDosagem(remedioDTO.getDosagem());

        // Duração
        String strDuracao = remedioDTO.getDuracao();
        if (strDuracao != null) {
            switch (strDuracao.toLowerCase()) {
                case "sempre":
                    remedio.setDuracao(Duracao.SEMPRE);
                    remedio.setDuracaoTempo(null);
                    remedio.setDuracaoDataFinal(null);
                    break;
                case "quantidade":
                    remedio.setDuracao(Duracao.POR);
                    // Tenta converter a duração em tempo (dias, semanas, meses)
                    try {
                        Integer duracao = remedioDTO.getDuracaoTempo(); 
                        int duracaoTempo = 0;
                        if (duracao != null) {
                            duracaoTempo = duracao;
                        }
                        remedio.setDuracaoTempo(duracaoTempo);
                    } catch (Exception e) {
                        remedio.setDuracaoTempo(0);
                    }
                    DateTime dataFinal = new DateTime(System.currentTimeMillis() + (remedio.getDuracaoTempo() * 24L * 60L * 60L * 1000L));
                    remedio.setDuracaoDataFinal(dataFinal != null ? new java.sql.Date(dataFinal.getValue()) : null);
                    break;
                case "data":
                    remedio.setDuracao(Duracao.ATE);
                    remedio.setDuracaoTempo(null);
                    // Tenta converter a data final
                    try {
                        String dataFinalStr = remedioDTO.getDuracaoDataFinal();
                        if (dataFinalStr != null && !dataFinalStr.isEmpty()) {
                            remedio.setDuracaoDataFinal(java.sql.Date.valueOf(dataFinalStr));
                        } else {
                            remedio.setDuracaoDataFinal(null);
                        }
                    } catch (Exception e) {
                        remedio.setDuracaoDataFinal(null);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Valor de duração inválido: " + strDuracao);
            }
        }

        // Repetição
        String strRepeticao = remedioDTO.getRepeticao();
        if (strRepeticao != null) {
            switch (strRepeticao.toLowerCase()) {
                case "diario":
                    remedio.setRepeticao(Repeticao.DIARIO);
                    // Tenta pegar repeticaoDias se existir
                    try {
                        String diasStr = remedioDTO.getRepeticaoSemana(); // campo não existe, mas pode vir como string
                        int dias = 0;
                        if (diasStr != null && !diasStr.isEmpty()) {
                            dias = Integer.parseInt(diasStr);
                        }
                        remedio.setRepeticaoDias(dias);
                    } catch (Exception e) {
                        remedio.setRepeticaoDias(0);
                    }
                    remedio.setRepeticaoSemana("");
                    break;
                case "semanal":
                    remedio.setRepeticao(Repeticao.SEMANAL);
                    remedio.setRepeticaoSemana(
                            remedioDTO.getRepeticaoSemana() != null ? remedioDTO.getRepeticaoSemana() : "");
                    remedio.setRepeticaoDias(0);
                    break;
                case "mensal":
                    remedio.setRepeticao(Repeticao.MENSAL);
                    remedio.setRepeticaoSemana(
                            remedioDTO.getRepeticaoSemana() != null ? remedioDTO.getRepeticaoSemana() : "");
                    remedio.setRepeticaoDias(0);
                    break;
                default:
                    throw new IllegalArgumentException("Valor de repetição inválido: " + strRepeticao);
            }
        }

        Usuario usuario = usuarioService.getUserByGoogleId(usuarioId);
        remedio.setUsuario(usuario);
        remedioRepository.save(remedio);

        remedio.setHorarios(cadastrarHorarios(remedioDTO.getHorario(), remedio.getId()));
        remedioRepository.save(remedio);

        System.out.println("-> Remédio cadastrado com sucesso! -> {" + remedio + "}");
    }

    public List<Remedio> listarRemediosPorUsuario(String googleId) {
        List<Remedio> remedios = remedioRepository.findByUsuarioGoogleId(googleId);
        System.out.println(
                "-> Encontrados " + remedios.size() + " remédios para o usuário com Google ID: " + googleId + "}");
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

    private List<Horario> cadastrarHorarios(List<String> horariosStr, UUID remedioId) {
        return horariosStr.stream().map(horarioStr -> {
            return horarioService.cadastrarHorario(remedioRepository.getById(remedioId), horarioStr);
        }).toList();
    }

}
