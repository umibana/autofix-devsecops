package com.example.autofix.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.autofix.entities.BonoEntity;
import com.example.autofix.entities.HistorialReparacionEntity;
import com.example.autofix.entities.ReporteCostoEntity;
import com.example.autofix.entities.VehiculoEntity;
import com.example.autofix.entities.VehiculoReparacionEntity;
import com.example.autofix.repositories.BonoRepository;
import com.example.autofix.repositories.HistorialReparacionRepository;
import com.example.autofix.repositories.ReporteCostoRepository;
import com.example.autofix.repositories.VehiculoReparacionRepository;
import com.example.autofix.repositories.VehiculoRepository;

@Service
public class GestionService {

    @Autowired
    VehiculoRepository vehiculoRepository;

    @Autowired
    HistorialReparacionRepository historialReparacionRepository;

    @Autowired
    VehiculoReparacionRepository vehiculoReparacionRepository;

    @Autowired
    BonoRepository bonoRepository;

    @Autowired
    ReporteCostoRepository reporteCostoRepository;

    public ArrayList<BonoEntity> getBonos(){
        return (ArrayList<BonoEntity>) bonoRepository.findAll();
    }

    public BonoEntity saveBono(BonoEntity bono){
        return bonoRepository.save(bono);
    }

    public ArrayList<VehiculoEntity> getVehiculos(){
        return (ArrayList<VehiculoEntity>) vehiculoRepository.findAll();
    }

    public VehiculoEntity saveVehiculo(VehiculoEntity vehiculo){
        return vehiculoRepository.save(vehiculo);
    }

    public ArrayList<VehiculoReparacionEntity> getVehiculosReparaciones(){
        return (ArrayList<VehiculoReparacionEntity>) vehiculoReparacionRepository.findAll();
    }

     public VehiculoReparacionEntity saveVehiculoReparacion(VehiculoReparacionEntity vehiculoReparacion){
        return vehiculoReparacionRepository.save(vehiculoReparacion);
    }

    public ArrayList<HistorialReparacionEntity> getHistoriales(){
        return (ArrayList<HistorialReparacionEntity>) historialReparacionRepository.findAll();
    }

    public HistorialReparacionEntity saveHistorialReparacion(HistorialReparacionEntity reparacion){
        return historialReparacionRepository.save(reparacion);
    }
    

    public int costoTotalReparacion(Long id_auto){
        List<VehiculoReparacionEntity> vehiculoReparacionesLista = vehiculoReparacionRepository.findByIdauto(id_auto);
        int costoTotal = 0;
        for(VehiculoReparacionEntity reparacionVehiculo:vehiculoReparacionesLista){
            costoTotal = costoTotal + (reparacionVehiculo.getCosto_reparacion());
        }

        return costoTotal;
    }

    public List<VehiculoReparacionEntity> getReparacionesVehiculo (Long id_auto){
        return (List<VehiculoReparacionEntity>) vehiculoReparacionRepository.findByIdauto(id_auto);
    }

    public int descuentoNumReparaciones (Long id_auto){
        //Descuento a aplicar
        int descuento = 0;
        
        //Cantidad de reparaciones (Historiales)
        List<HistorialReparacionEntity> historialesVehiculo = historialReparacionRepository.findByIdAutoOrderedByIdAsc(id_auto);
        int cant_reparaciones = historialesVehiculo.size();

        //A que vehiculo se le aplica el descuento y asi saber el tipo de motor
        VehiculoEntity vehiculo = vehiculoRepository.findById(id_auto).get();
        String tipo_motor = vehiculo.getTipo_motor();
        
        if (cant_reparaciones >= 1 && cant_reparaciones <= 2 && tipo_motor == "Gasolina"){
            descuento = 5;
        }
        else if (cant_reparaciones >= 3 && cant_reparaciones <= 5 && tipo_motor == "Gasolina"){
            descuento = 10;
        }
        else if (cant_reparaciones >= 6 && cant_reparaciones <= 9 && tipo_motor == "Gasolina"){
            descuento = 15;
        }
        else if (cant_reparaciones >= 10  && tipo_motor == "Gasolina"){
            descuento = 20;
        }
        else if (cant_reparaciones >= 1 && cant_reparaciones <= 2 && tipo_motor == "Diesel"){
            descuento = 7;
        }
        else if (cant_reparaciones >= 3 && cant_reparaciones <= 5 && tipo_motor == "Diesel"){
            descuento = 12;
        }
        else if (cant_reparaciones >= 6 && cant_reparaciones <= 9 && tipo_motor == "Diesel"){
            descuento = 17;
        }
        else if (cant_reparaciones >= 10  && tipo_motor == "Diesel"){
            descuento = 22;
        }
        else if (cant_reparaciones >= 1 && cant_reparaciones <= 2 && tipo_motor == "Hibrido"){
            descuento = 10;
        }
        else if (cant_reparaciones >= 3 && cant_reparaciones <= 5 && tipo_motor == "Hibrido"){
            descuento = 15;
        }
        else if (cant_reparaciones >= 6 && cant_reparaciones <= 9 && tipo_motor == "Hibrido"){
            descuento = 20;
        }
        else if (cant_reparaciones >= 10  && tipo_motor == "Hibrido"){
            descuento = 25;
        }
        else if (cant_reparaciones >= 1 && cant_reparaciones <= 2 && tipo_motor == "Electrico"){
            descuento = 8;
        }
        else if (cant_reparaciones >= 3 && cant_reparaciones <= 5 && tipo_motor == "Electrico"){
            descuento = 13;
        }
        else if (cant_reparaciones >= 6 && cant_reparaciones <= 9 && tipo_motor == "Electrico"){
            descuento = 18;
        }
        else if (cant_reparaciones >= 10  && tipo_motor == "Electrico"){
            descuento = 23;
        }

        return descuento;
    }

    public int descuentoDiaAtencion(Long id_auto){
        int descuento = 0;
        LocalTime horaInicio = LocalTime.of(9, 0); // 09:00
        LocalTime horaFin = LocalTime.of(12, 0); //12:00

        List<HistorialReparacionEntity> vehiculoHistorialLista = historialReparacionRepository.findByIdAutoOrderedByIdAsc(id_auto);
        HistorialReparacionEntity vehiculoHistorial = vehiculoHistorialLista.get(vehiculoHistorialLista.size()-1);

        String diaSemana = vehiculoHistorial.getFecha_ingreso().getDayOfWeek().name();

        LocalTime horaIngreso = vehiculoHistorial.getHora_ingreso();
        if (("LUNES".equalsIgnoreCase(diaSemana) || "MARTES".equalsIgnoreCase(diaSemana) || "MIERCOLES".equalsIgnoreCase(diaSemana) || "JUEVES".equalsIgnoreCase(diaSemana)) && horaIngreso.isAfter(horaInicio) && horaIngreso.isBefore(horaFin)) {
            descuento = 10;
        }
        return descuento;
    }

    public void actualizarFlagBono(Long id_auto){
        VehiculoEntity vehiculo = vehiculoRepository.findById(id_auto).get();
        BonoEntity bono = bonoRepository.findByMarca(vehiculo.getMarca());
        bono.setCantidad_bono((bono.getCantidad_bono())-1);
        bonoRepository.save(bono);
        vehiculo.setFbono("Si");
        vehiculoRepository.save(vehiculo);
    }

    public int descuentoBono(Long id_auto){
        int descuento = 0;
        VehiculoEntity vehiculo = vehiculoRepository.findById(id_auto).get();
        String marca = vehiculo.getMarca();
        BonoEntity tipo_bono = bonoRepository.findByMarca(marca);
        descuento = tipo_bono.getMonto_bono();
        return descuento;
    }

    public int recargoKilometraje(Long id_auto){
        //Descuento a aplicar
        int recargo = 0;


        //A que vehiculo se le aplica el recargo y asi saber el tipo de motor
        VehiculoEntity vehiculo = vehiculoRepository.findById(id_auto).get();
        int kilometraje_vehiculo = vehiculo.getKilometraje();
        String tipo_vehiculo = vehiculo.getTipo_vehiculo();
        
        if (kilometraje_vehiculo >= 0 && kilometraje_vehiculo <= 5000 && tipo_vehiculo == "Sedan"){
            recargo = 0;
        }
        else if (kilometraje_vehiculo >= 5001 && kilometraje_vehiculo <= 12000 && tipo_vehiculo == "Sedan"){
            recargo = 3;
        }
        else if (kilometraje_vehiculo >= 12001 && kilometraje_vehiculo <= 25000 && tipo_vehiculo == "Sedan"){
            recargo = 7;
        }
        else if (kilometraje_vehiculo >= 25001  && kilometraje_vehiculo <= 40000 && tipo_vehiculo == "Sedan"){
            recargo = 12;
        }
        else if (kilometraje_vehiculo >= 40000  &&  tipo_vehiculo == "Sedan"){
            recargo = 20;
        }

        else if (kilometraje_vehiculo >= 0 && kilometraje_vehiculo <= 5000 && tipo_vehiculo == "Hatchback"){
            recargo = 0;
        }
        else if (kilometraje_vehiculo >= 5001 && kilometraje_vehiculo <= 12000 && tipo_vehiculo == "Hatchback"){
            recargo = 3;
        }
        else if (kilometraje_vehiculo >= 12001 && kilometraje_vehiculo <= 25000 && tipo_vehiculo == "Hatchback"){
            recargo = 7;
        }
        else if (kilometraje_vehiculo >= 25001  && kilometraje_vehiculo <= 40000 && tipo_vehiculo == "Hatchback"){
            recargo = 12;
        }
        else if (kilometraje_vehiculo >= 40000  &&  tipo_vehiculo == "Hatchback"){
            recargo = 20;
        }

        else if (kilometraje_vehiculo >= 0 && kilometraje_vehiculo <= 5000 && tipo_vehiculo == "SUV"){
            recargo = 0;
        }
        else if (kilometraje_vehiculo >= 5001 && kilometraje_vehiculo <= 12000 && tipo_vehiculo == "SUV"){
            recargo = 5;
        }
        else if (kilometraje_vehiculo >= 12001 && kilometraje_vehiculo <= 25000 && tipo_vehiculo == "SUV"){
            recargo = 9;
        }
        else if (kilometraje_vehiculo >= 25001  && kilometraje_vehiculo <= 40000 && tipo_vehiculo == "SUV"){
            recargo = 12;
        }
        else if (kilometraje_vehiculo >= 40000  &&  tipo_vehiculo == "SUV"){
            recargo = 20;
        }

        else if (kilometraje_vehiculo >= 0 && kilometraje_vehiculo <= 5000 && tipo_vehiculo == "Pickup"){
            recargo = 0;
        }
        else if (kilometraje_vehiculo >= 5001 && kilometraje_vehiculo <= 12000 && tipo_vehiculo == "Pickup"){
            recargo = 5;
        }
        else if (kilometraje_vehiculo >= 12001 && kilometraje_vehiculo <= 25000 && tipo_vehiculo == "Pickup"){
            recargo = 9;
        }
        else if (kilometraje_vehiculo >= 25001  && kilometraje_vehiculo <= 40000 && tipo_vehiculo == "Pickup"){
            recargo = 12;
        }
        else if (kilometraje_vehiculo >= 40000  &&  tipo_vehiculo == "Pickup"){
            recargo = 20;
        }
        
        else if (kilometraje_vehiculo >= 0 && kilometraje_vehiculo <= 5000 && tipo_vehiculo == "Furgoneta"){
            recargo = 0;
        }
        else if (kilometraje_vehiculo >= 5001 && kilometraje_vehiculo <= 12000 && tipo_vehiculo == "Furgoneta"){
            recargo = 5;
        }
        else if (kilometraje_vehiculo >= 12001 && kilometraje_vehiculo <= 25000 && tipo_vehiculo == "Furgoneta"){
            recargo = 9;
        }
        else if (kilometraje_vehiculo >= 25001  && kilometraje_vehiculo <= 40000 && tipo_vehiculo == "Furgoneta"){
            recargo = 12;
        }
        else if (kilometraje_vehiculo >= 40000  &&  tipo_vehiculo == "Furgoneta"){
            recargo = 20;
        }

        return recargo;
    }

    public int recargoAntiguedad (Long id_auto){
        int recargo =0;

        //A que vehiculo se le aplica el recargo y asi saber el tipo de motor
        VehiculoEntity vehiculo = vehiculoRepository.findById(id_auto).get();

        int año_fabricacion_vehiculo = vehiculo.getAño_fabricacion();
        String tipo_vehiculo = vehiculo.getTipo_vehiculo();

        LocalDate fechaActual = LocalDate.now();
        int año_actual = fechaActual.getYear();

        int antiguedad_vehiculo = año_actual - año_fabricacion_vehiculo;

        if (antiguedad_vehiculo >= 0 && antiguedad_vehiculo <= 5 && tipo_vehiculo == "Sedan"){
            recargo = 0;
        }
        else if (antiguedad_vehiculo >= 6 && antiguedad_vehiculo <= 10 && tipo_vehiculo == "Sedan"){
            recargo = 5;
        }
        else if (antiguedad_vehiculo >= 11 && antiguedad_vehiculo <= 15 && tipo_vehiculo == "Sedan"){
            recargo = 9;
        }
        else if (antiguedad_vehiculo >= 16  &&  tipo_vehiculo == "Sedan"){
            recargo = 15;
        }

        else if (antiguedad_vehiculo >= 0 && antiguedad_vehiculo <= 5 && tipo_vehiculo == "Hatchback"){
            recargo = 0;
        }
        else if (antiguedad_vehiculo >= 6 && antiguedad_vehiculo <= 10 && tipo_vehiculo == "Hatchback"){
            recargo = 5;
        }
        else if (antiguedad_vehiculo >= 11 && antiguedad_vehiculo <= 15 && tipo_vehiculo == "Hatchback"){
            recargo = 9;
        }
        else if (antiguedad_vehiculo >= 16  &&  tipo_vehiculo == "Hatchback"){
            recargo = 15;
        }

        else if (antiguedad_vehiculo >= 0 && antiguedad_vehiculo <= 5 && tipo_vehiculo == "SUV"){
            recargo = 0;
        }
        else if (antiguedad_vehiculo >= 6 && antiguedad_vehiculo <= 10 && tipo_vehiculo == "SUV"){
            recargo = 7;
        }
        else if (antiguedad_vehiculo >= 11 && antiguedad_vehiculo <= 15 && tipo_vehiculo == "SUV"){
            recargo = 11;
        }
        else if (antiguedad_vehiculo >= 16  &&  tipo_vehiculo == "SUV"){
            recargo = 20;
        }

        else if (antiguedad_vehiculo >= 0 && antiguedad_vehiculo <= 5 && tipo_vehiculo == "Pickup"){
            recargo = 0;
        }
        else if (antiguedad_vehiculo >= 6 && antiguedad_vehiculo <= 10 && tipo_vehiculo == "Pickup"){
            recargo = 7;
        }
        else if (antiguedad_vehiculo >= 11 && antiguedad_vehiculo <= 15 && tipo_vehiculo == "Pickup"){
            recargo = 11;
        }
        else if (antiguedad_vehiculo >= 16  &&  tipo_vehiculo == "Pickup"){
            recargo = 20;
        }

        else if (antiguedad_vehiculo >= 0 && antiguedad_vehiculo <= 5 && tipo_vehiculo == "Furgoneta"){
            recargo = 0;
        }
        else if (antiguedad_vehiculo >= 6 && antiguedad_vehiculo <= 10 && tipo_vehiculo == "Furgoneta"){
            recargo = 7;
        }
        else if (antiguedad_vehiculo >= 11 && antiguedad_vehiculo <= 15 && tipo_vehiculo == "Furgoneta"){
            recargo = 11;
        }
        else if (antiguedad_vehiculo >= 16  &&  tipo_vehiculo == "Furgoneta"){
            recargo = 20;
        }

        return recargo;
    }

    public int recargoRetrasoRecogida(Long id_auto){
        int recargo = 0;
        List<HistorialReparacionEntity> vehiculoHistorialLista = historialReparacionRepository.findByIdAutoOrderedByIdAsc(id_auto);
        HistorialReparacionEntity vehiculoHistorial = vehiculoHistorialLista.get(vehiculoHistorialLista.size()-1);

        LocalDate fechaActual = LocalDate.now();
        long diasTranscurridos = Math.abs(ChronoUnit.DAYS.between(vehiculoHistorial.getFecha_salida(),fechaActual));

        recargo = (int) (recargo +  (5 * diasTranscurridos));

        return recargo;
    }

    public List<VehiculoReparacionEntity> guardarListaReparacionVehiculo (List<VehiculoReparacionEntity> lista_reparaciones){
        List<VehiculoReparacionEntity> reparacionNew = vehiculoReparacionRepository.saveAll(lista_reparaciones);
        return reparacionNew;
    }

    public int costoTotalconDescYRecargo(Long id_auto){
        int costoFinal = 0;
        // Monto de reparaciones a las cuales despues multiplicar por el % de los desc y recargos
        int costoTotalReparaciones = costoTotalReparacion(id_auto);

        int descNumRepa = descuentoNumReparaciones(id_auto);
        int descDiaAtencion = descuentoDiaAtencion(id_auto);
        int descBono = descuentoBono(id_auto);

        int recKilometraje = recargoKilometraje(id_auto);
        int recargoAntiguedad = recargoAntiguedad(id_auto);
        int recRetrasoRecogida = recargoRetrasoRecogida(id_auto);

        int recargo =(costoTotalReparaciones * recKilometraje / 100) + (costoTotalReparaciones * recargoAntiguedad / 100) + (costoTotalReparaciones * recRetrasoRecogida / 100);
        int descuento = (costoTotalReparaciones * descNumRepa / 100) + (costoTotalReparaciones * descDiaAtencion / 100) + (costoTotalReparaciones * descBono / 100);

        costoFinal = costoTotalReparaciones + recargo - descuento;

        return costoFinal;
    }

    public ArrayList<ReporteCostoEntity> getReporteCosto(){
        return (ArrayList<ReporteCostoEntity>) reporteCostoRepository.findAll();
    }

    public ReporteCostoEntity saveReporteCosto(Long id_auto){
        ReporteCostoEntity reporteCostoNew = new ReporteCostoEntity();
        reporteCostoNew.setId_auto(id_auto);

        List<VehiculoReparacionEntity> lista_reparaciones = getReparacionesVehiculo(id_auto);
        List<String> lista_nombres_reparaciones = new ArrayList<>();
        List<Integer> lista_costos_reparaciones= new ArrayList<>();

        for(VehiculoReparacionEntity reparacion:lista_reparaciones){
            lista_nombres_reparaciones.add(reparacion.getTipo_reparacion());
            lista_costos_reparaciones.add(reparacion.getCosto_reparacion());
        }

        reporteCostoNew.setLista_nombres_reparaciones(lista_nombres_reparaciones);
        reporteCostoNew.setLista_costos_reparaciones(lista_costos_reparaciones);
        reporteCostoNew.setDesc_num_repa(descuentoNumReparaciones(id_auto));
        reporteCostoNew.setDesc_dia_atencion(descuentoDiaAtencion(id_auto));
        reporteCostoNew.setDesc_bono(descuentoBono(id_auto));
        reporteCostoNew.setRec_kilometraje(recargoKilometraje(id_auto));
        reporteCostoNew.setRecargo_antiguedad(recargoAntiguedad(id_auto));
        reporteCostoNew.setRec_retraso_recogida(recargoRetrasoRecogida(id_auto));
        reporteCostoNew.setCosto_total_reparacion(costoTotalReparacion(id_auto));

        updateMontoHistoriaReparacion(id_auto);

        return reporteCostoRepository.save(reporteCostoNew);
    }

    /*
    Funcion utilizada posteriormente de haber registrado todas las reparaciones al vehiculo, tiene su propia vista para ajustar
    fecha y hora de la salida que va a necesitar la reparacion
     */
    public HistorialReparacionEntity guardarHistorialReparacionEntity(Long id_auto, LocalDate fecha_salida, LocalTime hora_salida){
        HistorialReparacionEntity historialReparacionEntityNew = new HistorialReparacionEntity();
        historialReparacionEntityNew.setFecha_ingreso(LocalDate.now());
        historialReparacionEntityNew.setHora_ingreso(LocalTime.now());
        historialReparacionEntityNew.setFecha_salida(fecha_salida);
        historialReparacionEntityNew.setHora_salida(hora_salida);
        historialReparacionEntityNew.setId_auto(id_auto);

        return historialReparacionRepository.save(historialReparacionEntityNew);
    }
    /*
     Funcion Utilizada para ingresar datos de fecha y hora cuando el cliente va a buscar el auto
     */
    public HistorialReparacionEntity updateHistoriaReparacionEntity(Long id_auto, LocalDate fecha_cliente, LocalTime hora_cliente){
        List<HistorialReparacionEntity> vehiculoHistorialLista = historialReparacionRepository.findByIdAutoOrderedByIdAsc(id_auto);
        HistorialReparacionEntity vehiculoHistorial = vehiculoHistorialLista.get(vehiculoHistorialLista.size()-1);

        vehiculoHistorial.setFecha_cliente_busco_auto(fecha_cliente);
        vehiculoHistorial.setHora_cliente_busco_auto(hora_cliente);
        saveReporteCosto(id_auto);

        return historialReparacionRepository.save(vehiculoHistorial);
    }

    public HistorialReparacionEntity updateMontoHistoriaReparacion(Long id_auto){
        List<HistorialReparacionEntity> vehiculoHistorialLista = historialReparacionRepository.findByIdAutoOrderedByIdAsc(id_auto);
        HistorialReparacionEntity vehiculoHistorial = vehiculoHistorialLista.get(vehiculoHistorialLista.size()-1);

        vehiculoHistorial.setMonto_reparacion(costoTotalconDescYRecargo(id_auto));

        return historialReparacionRepository.save(vehiculoHistorial);
    }
}
