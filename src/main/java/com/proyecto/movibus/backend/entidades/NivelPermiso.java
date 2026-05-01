package com.proyecto.movibus.backend.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "nivel_permiso")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class NivelPermiso {
    @Id
    private Integer codigo;

    @Column(name = "nivel_permiso")
    private String nivel;
}
