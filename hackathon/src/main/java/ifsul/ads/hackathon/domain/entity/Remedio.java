
package ifsul.ads.hackathon.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

import jakarta.persistence.Column;

import java.sql.Date;
import java.util.UUID;

import org.apache.tomcat.util.net.openssl.ciphers.Encryption;

import lombok.Data;

@Entity
@Data
public class Remedio {

    @Id
    private UUID id;

    // Paracetamol
    private String nome;

    // Remedio pra dor
    private String descricao;

    // imgur
    private String fotoUrl;

    // Usuario
    private Usuario usuario;

    // Ex 50mg
    private String dosagem;

    //diario -> mensal
    @Enumerated(EnumType.STRING)
    private Repeticao repeticao;

    // EX: A cada 2 dias
    private int repeticaoDias;

    // Ex: segunda terca quarta
    @Enumerated(EnumType.STRING)
    private Semana repeticaoSemana;

    //sempre - por x dias - ate x data
    @Enumerated(EnumType.STRING)
    private Duracao duracao;

    // EX: por 5 dias
    private Integer duracaoTempo;

    // EX: 19/09/25
    private Date  duracaoDataFinal;
    


    public Remedio() {
    }

    public Remedio(String nome, String descricao, String fotoUrl) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.descricao = descricao;
        this.fotoUrl = fotoUrl;
    }

}
