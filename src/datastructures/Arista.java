package datastructures;

public class Arista {
    private CiudadOD origen;
    private CiudadOD destino;
    private double peso;

    public Arista(CiudadOD origen, CiudadOD destino, double peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }

    public CiudadOD getOrigen() {
        return origen;
    }

    public CiudadOD getDestino() {
        return destino;
    }

    public double getPeso() {
        return peso;
    }
}
