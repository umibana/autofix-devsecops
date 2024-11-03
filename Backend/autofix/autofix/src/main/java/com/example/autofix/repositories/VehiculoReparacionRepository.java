package com.example.autofix.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.autofix.entities.VehiculoReparacionEntity;

@Repository
public interface VehiculoReparacionRepository extends JpaRepository<VehiculoReparacionEntity, Long>{
    
    List<VehiculoReparacionEntity> findByIdauto(Long auto);
}