package ifsul.ads.hackathon.domain.entity;

import java.time.LocalTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "horarios")
public class Horario {
    @Id
    private UUID id;

    @Column(name = "hora")
    private LocalTime hora;

    @ManyToOne
    @JoinColumn(name = "remedio_id", nullable = false)
    private Remedio remedio;

    public Horario(Remedio remedio, LocalTime hora) {
        this.id = UUID.randomUUID();
        this.hora = hora;
        this.remedio = remedio;
    }
}
