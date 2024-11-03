package com.example.autofix.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "reporte_costo")
@Getter
@Setter
public class ReporteCostoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_auto")
    private Long id_auto;
    
    @Column(name = "lista_nombres_reparaciones")
    private List<String> lista_nombres_reparaciones;

    @Column(name = "lista_costos_reparaciones")
    private List<Integer> lista_costos_reparaciones;


    @Column(name = "desc_num_repa")
    private int desc_num_repa;
    
    @Column(name = "desc_dia_atencion")
    private int desc_dia_atencion;

    @Column(name = "desc_bono")
    private int desc_bono;

    @Column(name = "rec_kilometraje")
    private int rec_kilometraje;

    @Column(name = "recargo_antiguedad")
    private int recargo_antiguedad;

    @Column(name = "rec_retraso_recogida")
    private int rec_retraso_recogida;

    @Column(name = "costo_total_reparacion")
    private int costo_total_reparacion;
}