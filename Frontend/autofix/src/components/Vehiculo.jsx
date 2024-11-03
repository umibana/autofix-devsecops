import React, { useState } from 'react';
import axios from 'axios';

export function Vehiculo() {
    const [patente, setPatente] = useState('');
    const [marca, setMarca] = useState('');
    const [modelo, setModelo] = useState('');
    const [tipoVehiculo, setTipoVehiculo] = useState('');
    const [añoFabricacion, setAñoFabricacion] = useState('');
    const [tipoMotor, setTipoMotor] = useState('');
    const [numAsientos, setNumAsientos] = useState('');
    const [kilometraje, setKilometraje] = useState('');

    const handleVolverHome = () => {
        window.location.href = "/";
    }

    const handleRegistrarVehiculo = async () => {
        try {
            await axios.post('http://localhost:8090/api/vehiculo', {
                patente,
                marca,
                modelo,
                tipo_vehiculo: tipoVehiculo,
                año_fabricacion: añoFabricacion,
                tipo_motor: tipoMotor,
                num_asientos: numAsientos,
                kilometraje
            });
        } catch (error) {
            console.error('Error al registrar el vehículo:', error);
        }
    };

    return (
        <div>
            <h2>Registro de Vehículo</h2>
            <form>
                <div>
                    <label htmlFor="patente">Patente:</label>
                    <input type="text" id="patente" value={patente} onChange={(e) => setPatente(e.target.value)} required pattern="[A-Za-z]{4}[0-9]{2}" title="Debe contener 4 letras seguidas de 2 números." />
                </div>

                <div>
                    <label htmlFor="marca">Marca:</label>
                    <input type="text" id="marca" value={marca} onChange={(e) => setMarca(e.target.value)} required pattern="Toyota|Kia|Honda|Ford|Chevrolet|Hyundai|BMW|Mercedes-Benz|Volkswagen|Audi|Nissan|Subaru|Tesla|Jeep|Mazda" />
                </div>

                <div>
                    <label htmlFor="modelo">Modelo:</label>
                    <input type="text" id="modelo" value={modelo} onChange={(e) => setModelo(e.target.value)} required />
                </div>

                <div>
                    <label htmlFor="tipoVehiculo">Tipo de Vehículo:</label>
                    <select id="tipoVehiculo" value={tipoVehiculo} onChange={(e) => setTipoVehiculo(e.target.value)} required>
                        <option value="">Seleccione</option>
                        <option value="Sedan">Sedan</option>
                        <option value="Hatchback">Hatchback</option>
                        <option value="SUV">SUV</option>
                        <option value="Pickup">Pickup</option>
                        <option value="Furgoneta">Furgoneta</option>
                    </select>
                </div>

                <div>
                    <label htmlFor="añoFabricacion">Año de Fabricación:</label>
                    <input type="number" id="añoFabricacion" value={añoFabricacion} onChange={(e) => setAñoFabricacion(e.target.value)} required min="2001" max="2024" />
                </div>

                <div>
                    <label htmlFor="tipoMotor">Tipo de Motor:</label>
                    <select id="tipoMotor" value={tipoMotor} onChange={(e) => setTipoMotor(e.target.value)} required>
                        <option value="">Seleccione</option>
                        <option value="Gasolina">Gasolina</option>
                        <option value="Diesel">Diesel</option>
                        <option value="Hibrido">Híbrido</option>
                        <option value="Electrico">Eléctrico</option>
                    </select>
                </div>

                <div>
                    <label htmlFor="numAsientos">Número de Asientos:</label>
                    <input type="number" id="numAsientos" value={numAsientos} onChange={(e) => setNumAsientos(e.target.value)} required min="1" max="10" />
                </div>

                <div>
                    <label htmlFor="kilometraje">Kilometraje:</label>
                    <input type="number" id="kilometraje" value={kilometraje} onChange={(e) => setKilometraje(e.target.value)} required min="0" />
                </div>
                <button type="button" onClick={handleRegistrarVehiculo}>Registrar Vehículo</button>
            </form>
            <button onClick={handleVolverHome}>Volver a la página de inicio</button>
        </div>
    )
}

export default Vehiculo;
