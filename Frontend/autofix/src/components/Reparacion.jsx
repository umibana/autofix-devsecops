import React, { useState, useEffect } from 'react';
import axios from 'axios';

function Reparacion() {
  const [autos, setAutos] = useState([]);
  const [idAutoSeleccionado, setIdAutoSeleccionado] = useState('');
  const [reparaciones, setReparaciones] = useState([]);
  const [tipoReparacion, setTipoReparacion] = useState('');
  const [costoReparacion, setCostoReparacion] = useState('');
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

  const handleTipoReparacionChange = (event) => {
    setTipoReparacion(event.target.value);
  };

  const handleCostoReparacionChange = (event) => {
    setCostoReparacion(event.target.value);
  };

  const handleIdAutoChange = (event) => {
    setIdAutoSeleccionado(event.target.value);
  };

  const handleAddReparacion = () => {
    const nuevaReparacion = {
      tipo_reparacion: tipoReparacion,
      costo_reparacion: costoReparacion,
      idauto: idAutoSeleccionado,
    };
    setReparaciones([...reparaciones, nuevaReparacion]);
    // Limpiar los campos después de agregar la reparación
    setTipoReparacion('');
    setCostoReparacion('');
  };

  const enviarReparaciones = async () => {
    try {
      await axios.post('http://localhost:8090/api/reparacion', reparaciones);
      console.log('Reparaciones enviadas correctamente');
      // Limpiar la lista de reparaciones después de enviarlas
      setReparaciones([]);
    } catch (error) {
      console.error('Error al enviar las reparaciones:', error);
    }
  };

  return (
    <div>
      <h2>Registro de Reparaciones</h2>
      <div>
        <label htmlFor="idAuto">Seleccione el Auto:</label>
        <select id="idAuto" value={idAutoSeleccionado} onChange={handleIdAutoChange}>
          <option value="">Seleccione un Auto</option>
          {autos.map((auto) => (
            <option key={auto.id} value={auto.id}>
              {auto.patente} - {auto.modelo}
            </option>
          ))}
        </select>
      </div>
      <div>
        <label htmlFor="tipoReparacion">Tipo de Reparación:</label>
        <input
          type="text"
          id="tipoReparacion"
          value={tipoReparacion}
          onChange={handleTipoReparacionChange}
        />
      </div>
      <div>
        <label htmlFor="costoReparacion">Costo de Reparación:</label>
        <input
          type="number"
          id="costoReparacion"
          value={costoReparacion}
          onChange={handleCostoReparacionChange}
        />
      </div>
      <button onClick={handleAddReparacion}>Agregar Reparación</button>
      <div>
        <h3>Reparaciones Registradas</h3>
        <ul>
          {reparaciones.map((reparacion, index) => (
            <li key={index}>
              Tipo: {reparacion.tipo_reparacion}, Costo: {reparacion.costo_reparacion}
            </li>
          ))}
        </ul>
        <button onClick={enviarReparaciones}>Enviar Reparaciones</button>
      </div>
      <button onClick={VolverHome}>Volver a la página de inicio</button>
    </div>
  );
}

export default Reparacion;
