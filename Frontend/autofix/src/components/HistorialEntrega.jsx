import React, { useState, useEffect } from 'react';
import axios from 'axios';

export function HistorialEntrega() {
    const [autos, setAutos] = useState([]);
    const [idAuto, setIdAuto] = useState('');
    const [fechaSalida, setFechaSalida] = useState('');
    const [horaSalida, setHoraSalida] = useState('');

    const VolverHome = () => {
        window.location.href = "/";
    }

    useEffect(() => {
        const obtenerAutos = async () => {
            try {
                const response = await axios.get('http://localhost:8090/api/vehiculo');
                setAutos(response.data);
            } catch (error) {
                console.error('Error al obtener la lista de autos:', error);
            }
        };
        obtenerAutos();
    }, []);

    const handleIdAutoChange = (event) => {
        setIdAuto(event.target.value);
    };

    const handleFechaSalidaChange = (event) => {
        setFechaSalida(event.target.value);
    };

    const handleHoraSalidaChange = (event) => {
        setHoraSalida(event.target.value);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const url = `http://localhost:8090/api/historial_reparacion/cliente/${idAuto}/${fechaSalida}/${horaSalida}`;
            const response = await axios.post(url);
            // Aquí puedes manejar la respuesta del servidor según lo necesites
        } catch (error) {
            console.error('Error al registrar la salida en el historial:', error);
            // Aquí puedes manejar el error según lo necesites
        }
    };
    

    return (
        <div>
            <h2>Registro de Entrega en Historial de Reparación</h2>
            <form id="salidaHistorialReparacionForm" onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="idAuto">Vehículo:</label>
                    <select id="idAuto" name="idAuto" value={idAuto} onChange={handleIdAutoChange} required>
                        <option value="">Seleccione un Auto</option>
                        {autos.map((auto) => (
                            <option key={auto.id} value={auto.id}>
                                {auto.patente} - {auto.modelo}
                            </option>
                        ))}
                    </select>
                </div>
                <div>
                    <label htmlFor="fechaSalida">Fecha de Entrega:</label>
                    <input type="date" id="fechaSalida" name="fechaSalida" value={fechaSalida} onChange={handleFechaSalidaChange} required />
                </div>
                <div>
                    <label htmlFor="horaSalida">Hora de Entrega:</label>
                    <input type="time" id="horaSalida" name="horaSalida" value={horaSalida} onChange={handleHoraSalidaChange} required />
                </div>
                <button type="submit">Guardar Historial Entrega</button>
            </form>
            <button onClick={VolverHome}>Volver a la página de inicio</button>
        </div>
    );
}

export default HistorialEntrega;
