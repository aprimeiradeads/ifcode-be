package ifsul.ads.hackathon.domain.dto;

import java.util.UUID;

public record RemedioCadastroDTO(
	String nome,
	String descricao,
	String fotoUrl,
	String dosagem,
	String repeticao,
	Integer repeticaoDias,
	String repeticaoSemana,
	String duracao,
	Integer duracaoTempo,
	String duracaoDataFinal,
	UUID usuarioId
) {
}
