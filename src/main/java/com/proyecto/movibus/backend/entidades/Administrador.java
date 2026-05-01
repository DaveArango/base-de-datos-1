package com.proyecto.movibus.backend.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "administrador")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Administrador {
    @Id
    private Integer id;

    @Column(name = "cargo")
    private String cargo;

    @ManyToOne
    @JoinColumn(name = "codigo_nivel_permiso", nullable = false)
    private NivelPermiso nivelPermiso;

    @ManyToOne
    @JoinColumn(name = "codigo_administrador", nullable = false)
    private Administrador administrador;

    @ManyToMany
    @JoinTable(
            name = "administrador_reporte",
            joinColumns = @JoinColumn(name = "codigo_administrador"),
            inverseJoinColumns = @JoinColumn(name = "codigo_reporte")
    )
    private Set<Reporte> reportes;
}
