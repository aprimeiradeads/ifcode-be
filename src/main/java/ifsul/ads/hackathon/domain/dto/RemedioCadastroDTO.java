package ifsul.ads.hackathon.domain.dto;

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
	private String fotoBase64;

	// Construtor padrão para desserialização
	public RemedioCadastroDTO() {
	}

	public RemedioCadastroDTO(String nome, String descricao, String repeticao, String dosagem, String duracao, String fotoBase64) {
		this.nome = nome;
		this.descricao = descricao;
		this.repeticao = repeticao;
		this.dosagem = dosagem;
		this.duracao = duracao;
		this.fotoBase64 = fotoBase64;
	}

	public RemedioCadastroDTO(String nome, String descricao, String dosagem, String repeticao, String repeticaoSemana,
			String duracao, String duracaoDataFinal, String fotoBase64) {
		this.nome = nome;
		this.descricao = descricao;
		this.dosagem = dosagem;
		this.repeticao = repeticao;
		this.repeticaoSemana = repeticaoSemana;
		this.duracao = duracao;
		this.duracaoDataFinal = duracaoDataFinal;
		this.fotoBase64 = fotoBase64;
	}

}
