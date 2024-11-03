package com.example.autofix.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.autofix.entities.HistorialReparacionEntity;


@Repository
public interface HistorialReparacionRepository extends JpaRepository<HistorialReparacionEntity, Long>{

    @Query("SELECT hr FROM HistorialReparacionEntity hr WHERE hr.id_auto = :idAuto ORDER BY hr.id ASC")
    List<HistorialReparacionEntity> findByIdAutoOrderedByIdAsc(@Param("idAuto") Long idAuto);
}