package com.example.autofix.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "vehiculo")
@Getter
@Setter
public class VehiculoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "patente")
    private String patente;//4 letras y 2 numeros

    @Column(name = "marca")
    private String marca;//Toyota,Kia,Honda,Ford,Chevrolet,Hyundai,BMW,Mercedes-Benz,Volkswagen,Audi,Nissan,Subaru,Tesla,Jeep,Mazda

    @Column(name = "modelo")
    private String modelo;//Aqui como por ejemplo Audi A3,Audi A4,Audi A5

    @Column(name = "tipo_vehiculo")
    private String tipo_vehiculo;//Sedan,Hatchback,SUV,Pickup,Furgoneta

    @Column(name = "año_fabricacion")
    private Integer año_fabricacion;//2006-2005-2001 

    @Column(name = "tipo_motor")
    private String tipo_motor;//Gasolina,Diesel,Hibrido,Electrico
    
    @Column(name = "num_asientos")
    private Integer num_asientos;

    @Column(name = "kilometraje")
    private Integer kilometraje;

    @Column(name = "fbono")
    private String fbono = "no";

}