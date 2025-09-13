
package ifsul.ads.hackathon.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

import java.sql.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "remedios")
public class Remedio {

    @Id
    private UUID id;

    // Paracetamol
    @Column(name = "nome")
    private String nome;

    // Remedio pra dor
    @Column(name = "descricao")
    private String descricao;

    // imgur
    @Column(name = "foto_url")
    private String fotoUrl;

    // Usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Ex 50mg
    @Column(name = "dosagem")
    private String dosagem;

    // diario -> mensal
    @Enumerated(EnumType.STRING)
    @Column(name = "repeticao")
    private Repeticao repeticao;

    // EX: A cada 2 dias
    @Column(name = "repeticao_dias")
    private int repeticaoDias;

    // Ex: segunda terca quarta
    @Column(name = "repeticao_semana")
    private String repeticaoSemana;

    // sempre - por x dias - ate x data
    @Enumerated(EnumType.STRING)
    @Column(name = "duracao")
    private Duracao duracao;

    // EX: por 5 dias
    @Column(name = "duracao_tempo")
    private Integer duracaoTempo;

    // EX: 19/09/25
    @Column(name = "duracao_data_final")
    private Date duracaoDataFinal;

}
