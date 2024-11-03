package com.example.autofix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.autofix.entities.BonoEntity;

@Repository
public interface BonoRepository extends JpaRepository<BonoEntity, Long>{

    BonoEntity findByMarca (String marca);
}