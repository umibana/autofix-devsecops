package com.example.autofix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.autofix.entities.ReporteCostoEntity;

@Repository
public interface ReporteCostoRepository extends JpaRepository<ReporteCostoEntity,Long>{
}