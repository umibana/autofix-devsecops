package com.example.autofix.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.autofix.entities.BonoEntity;
import com.example.autofix.entities.HistorialReparacionEntity;
import com.example.autofix.entities.VehiculoEntity;
import com.example.autofix.entities.VehiculoReparacionEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@SpringBootTest
public class GestionServiceTest {

    @Autowired
    private GestionService gestionService;

    @Test
    void testGetBonos() {
        //Given
        BonoEntity bono1 = new BonoEntity();
        bono1.setCantidad_bono(8);
        bono1.setMarca("Audi");
        bono1.setMonto_bono(50000);

        gestionService.saveBono(bono1);

        //When
        List<BonoEntity> bono_test = gestionService.getBonos();
        
        //Then
        boolean bono_encontrado = false;
        for (BonoEntity bono : bono_test) {
            if (bono.getMarca().equals(bono1.getMarca())) {
                bono_encontrado = true;
                break;
            }
        }
        assertTrue(bono_encontrado, "El bono no se encontró en la lista de bonos obtenida.");
    }

    @Test
    void testSaveBono() {
        BonoEntity bono = new BonoEntity();
        bono.setMarca("Toyota");
        bono.setCantidad_bono(5);
        bono.setMonto_bono(70000);
        assertThat(gestionService.saveBono(bono)).isNotNull();
    }

    @Test
    void testGetVehiculos() {
        //Given
        VehiculoEntity vehiculo1 = new VehiculoEntity();
        vehiculo1.setAño_fabricacion(2008);
        vehiculo1.setKilometraje(39493);
        vehiculo1.setMarca("Toyota");
        vehiculo1.setModelo("R7");
        vehiculo1.setNum_asientos(5);
        vehiculo1.setPatente("ABCD12");
        vehiculo1.setTipo_motor("Gasolina");
        vehiculo1.setTipo_vehiculo("SUV");

        gestionService.saveVehiculo(vehiculo1);

        //When
        List<VehiculoEntity> vehiculo_test = gestionService.getVehiculos();
        
        //Then
        boolean vehiculo_encontrado = false;
        for (VehiculoEntity vehiculo : vehiculo_test) {
            if (vehiculo.getPatente().equals(vehiculo1.getPatente())) {
                vehiculo_encontrado = true;
                break;
            }
        }
        assertTrue(vehiculo_encontrado, "El vehiculo no se encontró en la lista de vehiculos obtenida.");
    }

    @Test
    void testSaveVehiculo() {
        VehiculoEntity vehiculo1 = new VehiculoEntity();
        vehiculo1.setAño_fabricacion(2008);
        vehiculo1.setKilometraje(39493);
        vehiculo1.setMarca("Toyota");
        vehiculo1.setModelo("R7");
        vehiculo1.setNum_asientos(5);
        vehiculo1.setPatente("ABCD12");
        vehiculo1.setTipo_motor("Gasolina");
        vehiculo1.setTipo_vehiculo("SUV");
        assertThat(gestionService.saveVehiculo(vehiculo1)).isNotNull();
    }

    @Test
    void testGetVehiculosReparaciones() {
        //Given
        VehiculoReparacionEntity vehiculoReparacion1 = new VehiculoReparacionEntity();
        vehiculoReparacion1.setTipo_reparacion("Unos tornillos sueltos");
        vehiculoReparacion1.setCosto_reparacion(150000);
        vehiculoReparacion1.setIdauto(22L);

        gestionService.saveVehiculoReparacion(vehiculoReparacion1);

        //When
        List<VehiculoReparacionEntity> vehiculo_reparaciones_test = gestionService.getVehiculosReparaciones();
        
        //Then
        boolean vehiculo_encontrado = false;
        for (VehiculoReparacionEntity vehiculo_reparaciones : vehiculo_reparaciones_test) {
            if (vehiculo_reparaciones.getTipo_reparacion().equals(vehiculoReparacion1.getTipo_reparacion())) {
                vehiculo_encontrado = true;
                break;
            }
        }
        assertTrue(vehiculo_encontrado, "El vehiculo reparacion no se encontró en la lista de vehiculo reparacion obtenida.");
    }

    @Test
    void testSaveVehiculoReparacion() {
        VehiculoReparacionEntity vehiculoReparacion1 = new VehiculoReparacionEntity();
        vehiculoReparacion1.setTipo_reparacion("Unos tornillos sueltos");
        vehiculoReparacion1.setCosto_reparacion(150000);
        vehiculoReparacion1.setIdauto(22L);

        assertThat(gestionService.saveVehiculoReparacion(vehiculoReparacion1)).isNotNull();
    }

    @Test
    void testGetHistoriales() {
        //Given
        HistorialReparacionEntity historial1 = new HistorialReparacionEntity();
        historial1.setFecha_cliente_busco_auto(LocalDate.of(2004,1,30));
        historial1.setHora_cliente_busco_auto(LocalTime.of(13,23,3));
        historial1.setFecha_ingreso(LocalDate.of(2004,1,30));
        historial1.setHora_ingreso(LocalTime.of(13,43,3));
        historial1.setFecha_salida(LocalDate.of(2004,1,30));
        historial1.setHora_salida(LocalTime.of(13,52,3));
        historial1.setId_auto(2L);
        historial1.setMonto_reparacion(223434);

        gestionService.saveHistorialReparacion(historial1);

        //When
        List<HistorialReparacionEntity> historiales_test = gestionService.getHistoriales();
        
        //Then
        boolean historial_encontrado = false;
        for (HistorialReparacionEntity historial : historiales_test) {
            if (historial.getId_auto().equals(historial1.getId_auto())) {
                historial_encontrado = true;
                break;
            }
        }
        assertTrue(historial_encontrado, "El historial no se encontró en la lista de historiales obtenida.");
    }

    @Test
    void testSaveHistorialReparacion() {
        HistorialReparacionEntity historial1 = new HistorialReparacionEntity();
        historial1.setFecha_cliente_busco_auto(LocalDate.of(2004,1,30));
        historial1.setHora_cliente_busco_auto(LocalTime.of(13,30,3));
        historial1.setFecha_ingreso(LocalDate.of(2004,1,30));
        historial1.setHora_ingreso(LocalTime.of(13,30,3));
        historial1.setFecha_salida(LocalDate.of(2004,1,30));
        historial1.setHora_salida(LocalTime.of(13,30,3));
        historial1.setId_auto(2L);
        historial1.setMonto_reparacion(223434);
        assertThat(gestionService.saveHistorialReparacion(historial1)).isNotNull();
    }

    @Test
    public void testCostoTotalReparacion_shouldReturnTotalCostForExistingCar() {
        // Given
        Long idAuto = 5L; 

        VehiculoReparacionEntity reparacion1 = new VehiculoReparacionEntity();
        reparacion1.setIdauto(idAuto);
        reparacion1.setTipo_reparacion("Freno");
        reparacion1.setCosto_reparacion(200);
        gestionService.saveVehiculoReparacion(reparacion1);

        VehiculoReparacionEntity reparacion2 = new VehiculoReparacionEntity();
        reparacion2.setIdauto(idAuto);
        reparacion2.setTipo_reparacion("Aceite");
        reparacion2.setCosto_reparacion(100);
        gestionService.saveVehiculoReparacion(reparacion2);

        // When
        int costoTotal = gestionService.costoTotalReparacion(idAuto);

        // Then
        assertThat(costoTotal).isGreaterThanOrEqualTo(300);
    }

    @Test
    public void testCostoTotalReparacion_shouldReturnZeroForNonExistentCar() {
        // Given
        Long idAuto = 10L; // Replace with an ID that likely doesn't exist

        // When
        int costoTotal = gestionService.costoTotalReparacion(idAuto);

        // Then
        assertThat(costoTotal).isEqualTo(0);
    }

    @Test
    public void testGetReparacionesVehiculo_shouldReturnAllForExistingCar() {
        // Given
        Long idAuto = 5L;

        VehiculoReparacionEntity reparacion1 = new VehiculoReparacionEntity();
        reparacion1.setIdauto(idAuto);
        reparacion1.setTipo_reparacion("Freno");
        reparacion1.setCosto_reparacion(200);
        gestionService.saveVehiculoReparacion(reparacion1);

        VehiculoReparacionEntity reparacion2 = new VehiculoReparacionEntity();
        reparacion2.setIdauto(idAuto);
        reparacion2.setTipo_reparacion("Aceite");
        reparacion2.setCosto_reparacion(100);
        gestionService.saveVehiculoReparacion(reparacion2);

        // When
        List<VehiculoReparacionEntity> reparaciones = gestionService.getReparacionesVehiculo(idAuto);

        // Then
        assertThat(reparaciones).isNotEmpty();
        assertThat(reparaciones.size()).isGreaterThanOrEqualTo(2);
    }
}
