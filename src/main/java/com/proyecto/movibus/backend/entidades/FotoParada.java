package com.proyecto.movibus.backend.entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "foto_parada")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FotoParada {
    @Id
    private Integer codigo;

    @Column(name = "url")
    private String url;

    @ManyToOne
    @JoinColumn(name = "codigo_parada", nullable = false)
    private Parada parada;
}
