package ifsul.ads.hackathon.domain.dto;

import java.util.List;

import lombok.Data;

@Data
public class RemedioCadastroDTO {

	private String nome;
	private String descricao;
	private String repeticao;
	private String dosagem;
	private String duracao;
	private String repeticaoSemana;
	private String duracaoDataFinal;
	private String fotoUrl;
	private List<String> horario;

	// Construtor padrão para desserialização
	public RemedioCadastroDTO() {
	}

	// Caso 1: semanal, duracao quantidade
	public RemedioCadastroDTO(String nome, String descricao, String fotoUrl, String dosagem, String repeticao,
			String repeticaoSemana, String duracao, Integer duracaoTempo, String duracaoDataFinal) {
		this.nome = nome;
		this.descricao = descricao;
		this.fotoUrl = fotoUrl;
		this.dosagem = dosagem;
		this.repeticao = repeticao;
		this.repeticaoSemana = repeticaoSemana;
		this.duracao = duracao;
		this.duracaoDataFinal = duracaoDataFinal;
		// duracaoTempo não é campo da classe, mas pode ser usado para lógica futura
	}

	// Caso 2: mensal, duracao data
	// Removido construtor duplicado para evitar conflito de assinatura

	// Caso 3: diario, duracao sempre, repeticaoDias
	public RemedioCadastroDTO(String nome, String descricao, String fotoUrl, String dosagem, String repeticao,
			Integer repeticaoDias, String repeticaoSemana, String duracao, String duracaoDataFinal) {
		this.nome = nome;
		this.descricao = descricao;
		this.fotoUrl = fotoUrl;
		this.dosagem = dosagem;
		this.repeticao = repeticao;
		this.repeticaoSemana = repeticaoSemana;
		this.duracao = duracao;
		this.duracaoDataFinal = duracaoDataFinal;
		// repeticaoDias não é campo da classe, mas pode ser usado para lógica futura
	}

	public RemedioCadastroDTO(String nome, String descricao, String repeticao, String dosagem, String duracao,
			String fotoUrl) {
		this.nome = nome;
		this.descricao = descricao;
		this.repeticao = repeticao;
		this.dosagem = dosagem;
		this.duracao = duracao;
		this.fotoUrl = fotoUrl;
	}

	// Removido construtor duplicado para evitar conflito de assinatura

}
