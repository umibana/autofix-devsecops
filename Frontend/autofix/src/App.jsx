import { useState } from 'react'
import { BrowserRouter, Route, Routes } from "react-router-dom";
import HomePage from './components/Home.jsx';
import VehiculoPage from './components/Vehiculo.jsx';
import ReparacionPage from './components/Reparacion.jsx';
import HistorialSalidaPage from './components/HistorialSalida.jsx';
import HistorialEntregaPage from './components/HistorialEntrega.jsx';
import BonoPage from './components/Bono.jsx';
import ReporteCostoPage from './components/ReporteCosto.jsx';
import './App.css'

function App() {
  return (
    <div>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<HomePage/>}/>
        <Route path="/vehiculo" element={<VehiculoPage/>}/>
        <Route path="/reparacion" element={<ReparacionPage/>}/>
        <Route path="/historial-salida" element={<HistorialSalidaPage/>}/>
        <Route path="/historial-entrega" element={<HistorialEntregaPage/>}/>
        <Route path="/bono" element={<BonoPage/>}/>
        <Route path="/reporte-costo" element={<ReporteCostoPage/>}/>
      </Routes>
    </BrowserRouter>
    </div>
  )
}

export default App
