package ifsul.ads.hackathon.domain.dto;

import lombok.Data;

@Data
public class RemedioCadastroDTO {

	private String nome;
	private String descricao;
	private String repeticao;
	private String dosagem;
	private String duracao;

	// Construtor padrão para desserialização
	public RemedioCadastroDTO() {
	}

	public RemedioCadastroDTO(String nome, String descricao, String repeticao, String dosagem, String duracao) {
		this.nome = nome;
		this.descricao = descricao;
		this.repeticao = repeticao;
		this.dosagem = dosagem;
		this.duracao = duracao;
	}

}
