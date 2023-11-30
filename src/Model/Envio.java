package Model;

import datastructures.EstadoEnvio;
import datastructures.CiudadOD;

import java.util.ArrayList;
import java.util.List;

public class Envio {
    private String numeroSeguimiento;
    private CiudadOD origen;
    private CiudadOD destino;
    private CiudadOD ubicacionActual;
    private EstadoEnvio.Estado estado;
    private List<CiudadOD> historialCiudades;

    public Envio(InformacionPaquete informacionPaquete, CiudadOD origen, CiudadOD destino) {
        this.numeroSeguimiento = informacionPaquete.getId() + "-" + System.currentTimeMillis(); // Crear un número de seguimiento único
        this.origen = origen;
        this.destino = destino;
        this.ubicacionActual = origen;
        this.estado = EstadoEnvio.Estado.EN_PROCESO;
        this.historialCiudades = new ArrayList<>();
        this.historialCiudades.add(origen);
    }

    public String getNumeroSeguimiento() {
        return numeroSeguimiento;
    }

    public CiudadOD getOrigen() {
        return origen;
    }

    public CiudadOD getDestino() {
        return destino;
    }

    public CiudadOD getUbicacionActual() {
        return ubicacionActual;
    }

    public void setUbicacionActual(CiudadOD nuevaUbicacion) {
        this.ubicacionActual = nuevaUbicacion;
        this.historialCiudades.add(nuevaUbicacion); // Agregar la nueva ubicación al historial
    }

    public EstadoEnvio.Estado getEstado() {
        return estado;
    }

    public void setEstado(EstadoEnvio.Estado estado) {
        this.estado = estado;
    }

    public List<CiudadOD> getHistorialCiudades() {
        return new ArrayList<>(historialCiudades);
    }

	public void setNumeroSeguimiento(String numeroSeguimiento) {
		this.numeroSeguimiento = numeroSeguimiento;
	}

	public void setOrigen(CiudadOD origen) {
		this.origen = origen;
	}

	public void setDestino(CiudadOD destino) {
		this.destino = destino;
	}

	public void setHistorialCiudades(List<CiudadOD> historialCiudades) {
		this.historialCiudades = historialCiudades;
	}

}
