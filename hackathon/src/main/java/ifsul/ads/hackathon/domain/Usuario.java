package ifsul.ads.hackathon.domain;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    private UUID id;

    @Column(nullable = false)
    @Size(min = 3, message = "O nome deve ter pelo menos 3 caracteres")
    private String nome;

    @Column(nullable = false, unique = true)
    @Size(min = 3, message = "O login deve ter pelo menos 3 caracteres")
    private String login;

    @Column(nullable = false)
    @Size(min = 3, message = "A senha deve ter pelo menos 3 caracteres")
    private String senha;

    @Column(nullable = true, unique = true, length = 11)
    @Size(min = 11, max = 11, message = "O celular deve ter 11 caracteres")
    
    private String celular;

    public Usuario(String nome, String login, String senha, String celular) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.celular = celular;
    }
}
