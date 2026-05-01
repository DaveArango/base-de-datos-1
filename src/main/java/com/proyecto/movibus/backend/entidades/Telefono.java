package com.proyecto.movibus.backend.entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "telefono")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Telefono {
    @Id
    private Integer codigo;

    @Column(name = "numero")
    private String numero;
}
