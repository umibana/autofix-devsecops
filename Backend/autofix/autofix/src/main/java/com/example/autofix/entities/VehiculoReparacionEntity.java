package com.example.autofix.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "vehiculo_reparacion")
@Getter
@Setter
public class VehiculoReparacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tipo_reparacion", nullable = false, length = 255)
    private String tipo_reparacion;

    @Column(name = "costo_reparacion", nullable = false)
    private Integer costo_reparacion;

    @Column(name = "idauto", nullable = false)
    private Long idauto;
}