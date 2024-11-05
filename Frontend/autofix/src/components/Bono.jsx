import { useState, useEffect } from 'react';
import axios from 'axios';


const BonoForm = () => {
    const [bono, setBono] = useState({ marca: '', cantidad_bono: 0, monto_bono: 0 });
  
    const handleChange = (e) => {
      setBono({ ...bono, [e.target.name]: e.target.value });
    };
  
    const handleSubmit = async (e) => {
      e.preventDefault();
      try {
        const response = await axios.post('http://localhost:8090/api/bono', bono);
        console.log('Nuevo bono creado!:', bono);
        setBono({ marca: '', cantidad_bono: 0, monto_bono: 0 });
        console.log(response)
      } catch (error) {
        console.error('Error guardando bono:', error);
      }
    };
  
    return (
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="marca">Marca:</label>
          <input type="text" id="marca" name="marca" value={bono.marca} onChange={handleChange} required />
        </div>
        <div>
          <label htmlFor="cantidad_bono">Cantidad de Bono:</label>
          <input
            type="number"
            id="cantidad_bono"
            name="cantidad_bono"
            value={bono.cantidad_bono}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label htmlFor="monto_bono">Monto de Bono:</label>
          <input
            type="number"
            id="monto_bono"
            name="monto_bono"
            value={bono.monto_bono}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit">Save Bono</button>
      </form>
    );
  };

export function Bono() {
    const [autos, setAutos] = useState([]);
    const [idAuto, setIdAuto] = useState('');
    const VolverHome = () => {
        window.location.href = "/";
    }

    useEffect(() => {
        const obtenerAutos = async () => {
            try {
                const response = await axios.get('http://localhost:8090/api/vehiculo');
                // Filtrar autos con fbono="no"
                const autosSinBono = response.data.filter(auto => auto.fbono === "no");
                setAutos(autosSinBono);
            } catch (error) {
                console.error('Error al obtener la lista de autos:', error);
            }
        };
        obtenerAutos();
    }, []);

    const handleIdAutoChange = (event) => {
        setIdAuto(event.target.value);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const url = `http://localhost:8090/api/vehiculo/bono/${idAuto}`;
            const response = await axios.post(url);
            // Aquí puedes manejar la respuesta del servidor según lo necesites
        } catch (error) {
            console.error('Error al registrar la salida en el historial:', error);
            // Aquí puedes manejar el error según lo necesites
        }
    };

    return (
        <div>
            <h2> Crear un bono</h2>
            <BonoForm/>
            <h2>Asignar Bono auto</h2>
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
                <button type="submit">Asignar</button>
            </form>
            <button onClick={VolverHome}>Volver a la página de inicio</button>
        </div>
    )
}
export default Bono;