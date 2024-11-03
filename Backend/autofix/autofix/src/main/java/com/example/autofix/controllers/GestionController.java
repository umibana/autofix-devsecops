package com.example.autofix.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.autofix.entities.BonoEntity;
import com.example.autofix.entities.HistorialReparacionEntity;
import com.example.autofix.entities.ReporteCostoEntity;
import com.example.autofix.entities.VehiculoEntity;
import com.example.autofix.entities.VehiculoReparacionEntity;
import com.example.autofix.services.GestionService;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class GestionController {

    @Autowired
    GestionService  gestionService;
    
    @GetMapping("/vehiculo")
    public ResponseEntity<List<VehiculoEntity>> getVehiculos(){
        List<VehiculoEntity> vehiculos = gestionService.getVehiculos();
        return ResponseEntity.ok(vehiculos);
    }

    //Vista para ingresar un auto
    @PostMapping("/vehiculo")
    public ResponseEntity<VehiculoEntity> saveVehiculo(@RequestBody VehiculoEntity vehiculo){
        VehiculoEntity vehiculoNew = gestionService.saveVehiculo(vehiculo);
        return ResponseEntity.ok(vehiculoNew);
    }

    @GetMapping("/reparacion")
    public ResponseEntity<List<VehiculoReparacionEntity>> getVehiculoReparaciones(){
        List<VehiculoReparacionEntity> vehiculaReparcion = gestionService.getVehiculosReparaciones();
        return ResponseEntity.ok(vehiculaReparcion);
    }

    //Vista donde se le asignan las reparaciones al auto a eleccion
    @PostMapping("/reparacion")
    public ResponseEntity<List<VehiculoReparacionEntity>> guardarListaReparacionesVehiculo(@RequestBody List<VehiculoReparacionEntity> reparaciones){
        List<VehiculoReparacionEntity> reparacionesGuardadas = gestionService.guardarListaReparacionVehiculo(reparaciones);
        return ResponseEntity.ok(reparacionesGuardadas);
    }

    @GetMapping("/bono")
    public ResponseEntity<List<BonoEntity>> getBonos(){
        List<BonoEntity> bonos = gestionService.getBonos();
        return ResponseEntity.ok(bonos);
    }

    @PostMapping("/bono")
    public ResponseEntity<BonoEntity> saveBono(@RequestBody BonoEntity bono){
        BonoEntity bonoNew = gestionService.saveBono(bono);
        return ResponseEntity.ok(bonoNew);
    }

    //Vista de actualizar el bono a un auto
    @PostMapping("/vehiculo/bono/{vehiculoId}")
    public ResponseEntity<Void> actualizacionBono(@PathVariable Long vehiculoId){
        gestionService.actualizarFlagBono(vehiculoId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/historial_reparacion")
    public ResponseEntity<List<HistorialReparacionEntity>> getHistorialReaparaciones(){
        List<HistorialReparacionEntity> reparaciones = gestionService.getHistoriales();
        return ResponseEntity.ok(reparaciones);
    }

    @PostMapping("/historial_reparacion")
    public ResponseEntity<HistorialReparacionEntity> saveHistorialReparacion(@RequestBody HistorialReparacionEntity reparacion){
        HistorialReparacionEntity historialNew = gestionService.saveHistorialReparacion(reparacion);
        return ResponseEntity.ok(historialNew);
    }

    //Vista donde se creara el historial de reparacion de dicho auto en ese momento (Solo fecha/hora ingreso, fecha/hora salida y id auto del historial)
    @PostMapping("/historial_reparacion/salida/{vehiculoId}/{fechaSalida}/{horaSalida}")
    public ResponseEntity<HistorialReparacionEntity> guardarSalidaHistorialReparacion(@PathVariable Long vehiculoId, 
                                                                                    @PathVariable LocalDate fechaSalida, 
                                                                                    @PathVariable LocalTime horaSalida){
        HistorialReparacionEntity historialSalidaNew = gestionService.guardarHistorialReparacionEntity(vehiculoId, fechaSalida, horaSalida);
        return ResponseEntity.ok(historialSalidaNew);
    }

    //Vista donde se actualizara el historial de reparacion de dicho auto en ese momento (Se agrega fecha/hora cliente y se ingresa el monto)
    @PostMapping("/historial_reparacion/cliente/{vehiculoId}/{fechaCliente}/{horaCliente}")
    public ResponseEntity<HistorialReparacionEntity> actualizacionHistorialReparacion(@PathVariable Long vehiculoId,@PathVariable LocalDate fechaCliente, @PathVariable LocalTime horaCliente){
        HistorialReparacionEntity reparacionUpdated = gestionService.updateHistoriaReparacionEntity(vehiculoId,fechaCliente,horaCliente);
        return ResponseEntity.ok(reparacionUpdated);
    }

    //Vista donde se mostrara el reporte 1
    @GetMapping("/reporte_costo")
    public ResponseEntity<List<ReporteCostoEntity>> getReportesCosto(){
        List<ReporteCostoEntity> reportes = gestionService.getReporteCosto();
        return ResponseEntity.ok(reportes);
    }

}
