package com.example.autofix.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "historial_reparacion")
@Getter
@Setter
public class HistorialReparacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fecha_ingreso")
    private LocalDate fecha_ingreso;

    @Column(name = "hora_ingreso")
    private LocalTime hora_ingreso;

    @Column(name = "monto_reparacion")
    private Integer monto_reparacion;
    
    @Column(name = "fecha_salida")
    private LocalDate fecha_salida;

    @Column(name = "hora_salida")
    private LocalTime hora_salida;

    @Column(name = "fecha_cliente_busco_auto")
    private LocalDate fecha_cliente_busco_auto;
    
    @Column(name = "hora_cliente_busco_auto")
    private LocalTime hora_cliente_busco_auto;
    
    @Column(name = "id_auto")
    private Long id_auto;

}