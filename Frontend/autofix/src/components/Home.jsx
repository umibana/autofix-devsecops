export function Home() {
    const IngresarVehiculo = () => {
        window.location.href = "/vehiculo";
    }
    const IngresarReparaciones = () => {
        window.location.href = "/reparacion";
    }
    const IngresarHistorialSalida = () => {
        window.location.href = "/historial-salida";
    }
    const IngresarHistorialEntrega = () => {
        window.location.href = "/historial-entrega";
    }
    const IngresarBono = () => {
        window.location.href = "/bono";
    }
    const IngresarReporteCosto = () => {
        window.location.href = "/reporte-costo";
    }

    return (
        <div className={"home-proyecto"}>
            <h1 className={"home-proyecto-nombre"}>Autofix</h1>
            <div className={"opcion-container"}>
                <button className={"home-proyecto-opcion"} onClick={IngresarVehiculo}>Vehiculo</button>
                <button className={"home-proyecto-opcion"} onClick={IngresarReparaciones}>Reparacion</button>
                <button className={"home-proyecto-opcion"} onClick={IngresarHistorialSalida}>Historial salida</button>
                <button className={"home-proyecto-opcion"} onClick={IngresarHistorialEntrega}>Historial entrega</button>
                <button className={"home-proyecto-opcion"} onClick={IngresarBono}>Bono</button>
                <button className={"home-proyecto-opcion"} onClick={IngresarReporteCosto}>Reporte</button>
            </div>
            <footer className={"home-proyecto-footer"}>USACH 2024 - Departamento de informatica</footer>
        </div>
    )
}
export default Home;