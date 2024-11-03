import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../css/ReporteCosto.css';


function ReporteCosto() {
    const [reportesCosto, setReportesCosto] = useState([]);
    const VolverHome = () => {
        window.location.href = "/";
    }

    useEffect(() => {
        const fetchReportesCosto = async () => {
            try {
                const response = await axios.get('http://localhost:8090/api/reporte_costo');
                setReportesCosto(response.data);
            } catch (error) {
                console.error('Error al obtener los reportes de costo:', error);
            }
        };

        fetchReportesCosto();
    }, []);

    return (
        <div>
            <h2>Reporte de Costo</h2>
            <table className="reporte-costo-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>ID Auto</th>
                        <th>Lista de Nombres de Reparaciones</th>
                        <th>Lista de Costos de Reparaciones</th>
                        <th>Descuento por Número de Reparaciones</th>
                        <th>Descuento por Día de Atención</th>
                        <th>Descuento por Bono</th>
                        <th>Recargo de Kilometraje</th>
                        <th>Recargo de Antigüedad</th>
                        <th>Recargo de Retraso de Recogida</th>
                        <th>Costo Total de Reparación</th>
                    </tr>
                </thead>
                <tbody>
                    {reportesCosto.map(reporte => (
                        <tr key={reporte.id}>
                            <td>{reporte.id}</td>
                            <td>{reporte.id_auto}</td>
                            <td>{reporte.lista_nombres_reparaciones.join(', ')}</td>
                            <td>{reporte.lista_costos_reparaciones.join(', ')}</td>
                            <td>{reporte.desc_num_repa}</td>
                            <td>{reporte.desc_dia_atencion}</td>
                            <td>{reporte.desc_bono}</td>
                            <td>{reporte.rec_kilometraje}</td>
                            <td>{reporte.recargo_antiguedad}</td>
                            <td>{reporte.rec_retraso_recogida}</td>
                            <td>{reporte.costo_total_reparacion}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <button onClick={VolverHome}>Volver a la página de inicio</button>
        </div>
    );
}

export default ReporteCosto;
