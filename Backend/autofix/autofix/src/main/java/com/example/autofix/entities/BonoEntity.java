package com.example.autofix.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "bono")
@Getter
@Setter
public class BonoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "marca")
    private String marca;

    @Column(name = "cantidad_bono")
    private int cantidad_bono;
    
    @Column(name = "monto_bono")
    private int monto_bono;
}