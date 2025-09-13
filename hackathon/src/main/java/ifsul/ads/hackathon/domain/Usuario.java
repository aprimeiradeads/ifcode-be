package ifsul.ads.hackathon.domain;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = true)
    private String celular;

    public Usuario(String nome, String login, String senha, String celular) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.celular = celular;
    }
}
