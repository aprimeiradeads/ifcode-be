package ifsul.ads.hackathon.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name = "usuarios")
public class Usuario {

    @Id
    private String googleId;

    @Column
    private String email;

    @Column
    private String name;

}
