package com.example.autofix.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.example.autofix.entities.BonoEntity;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class BonoRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BonoRepository bonoRepository;

    @Test
    public void testFindByMarca_shouldReturnBonoEntity() {
        // Given
        String marca = "Toyota";
        BonoEntity bono = new BonoEntity();
        bono.setMarca(marca);
        bono.setCantidad_bono(10);
        bono.setMonto_bono(30000);
        entityManager.persistAndFlush(bono);

        // When
        BonoEntity foundBono = bonoRepository.findByMarca(marca);

        // Then
        assertThat(foundBono).isNotNull();
        assertThat(foundBono.getMarca()).isEqualTo(marca);
        assertThat(foundBono.getCantidad_bono()).isEqualTo(bono.getCantidad_bono());
        assertThat(foundBono.getMonto_bono()).isEqualTo(bono.getMonto_bono());
    }
}
