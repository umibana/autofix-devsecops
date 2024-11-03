package com.example.autofix.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.autofix.entities.VehiculoEntity;

@Repository
public interface VehiculoRepository extends JpaRepository<VehiculoEntity, Long>{
    
    List<VehiculoEntity> findByFbonoLike(String flagBono);//Me servira cuando desee ver el Descuento de Bono
}