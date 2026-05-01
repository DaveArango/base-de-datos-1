package com.proyecto.movibus.backend.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "metodo_pago")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MetodoPago {
    @Id
    private Integer codigo;

    @Column(name = "metodo_pago")
    private String metodo;
}
