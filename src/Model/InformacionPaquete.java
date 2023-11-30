package Model;

import datastructures.CiudadOD;


public class InformacionPaquete {
    private int id;
    private String contenido;
    private double peso;
    private double dimensiones;
    private CiudadOD origen;
    private CiudadOD destino;

    public InformacionPaquete(int id, String contenido, double peso, double dimensiones, CiudadOD origen, CiudadOD destino) {
        this.id = id;
        this.contenido = contenido;
        this.peso = peso;
        this.dimensiones = dimensiones;
        this.origen = origen;
        this.destino = destino;
    }


    public int getId() {
        return id;
    }

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getDimensiones() {
		return dimensiones;
	}

	public void setDimensiones(double dimensiones) {
		this.dimensiones = dimensiones;
	}

	public CiudadOD getOrigen() {
		return origen;
	}

	public void setOrigen(CiudadOD origen) {
		this.origen = origen;
	}

	public CiudadOD getDestino() {
		return destino;
	}

	public void setDestino(CiudadOD destino) {
		this.destino = destino;
	}

	public void setId(int id) {
		this.id = id;
	}
    
    
}


