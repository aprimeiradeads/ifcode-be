
package ifsul.ads.hackathon.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    @Lob
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

    @OneToMany(mappedBy = "remedio", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @JsonManagedReference
    private List<Horario> horarios;

}
