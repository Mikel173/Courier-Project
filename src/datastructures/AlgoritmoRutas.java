package datastructures;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class AlgoritmoRutas {
    public static List<CiudadOD> encontrarRutaOptima(GrafoPonderado grafo, CiudadOD inicio, CiudadOD destino) {
        Map<CiudadOD, Double> distancias = new HashMap<>();
        Map<CiudadOD, CiudadOD> padres = new HashMap<>();

        for (CiudadOD ciudad : grafo.obtenerCiudades()) {
            distancias.put(ciudad, Double.MAX_VALUE);
        }

        distancias.put(inicio, 0.0);

        PriorityQueue<NodoDistancia> colaPrioridad = new PriorityQueue<>();
        colaPrioridad.add(new NodoDistancia(inicio, 0.0));

        while (!colaPrioridad.isEmpty()) {
            CiudadOD actual = colaPrioridad.poll().getCiudad();

            for (Arista arista : grafo.obtenerAristas(actual)) {
                CiudadOD vecino = arista.getDestino();
                double nuevaDistancia = distancias.get(actual) + arista.getPeso();

                if (nuevaDistancia < distancias.get(vecino)) {
                    distancias.put(vecino, nuevaDistancia);
                    padres.put(vecino, actual);
                    colaPrioridad.add(new NodoDistancia(vecino, nuevaDistancia));
                }
            }
        }

        return reconstruirRutaOptima(padres, inicio, destino);
    }

    private static List<CiudadOD> reconstruirRutaOptima(Map<CiudadOD, CiudadOD> padres, CiudadOD inicio, CiudadOD destino) {
        List<CiudadOD> rutaOptima = new LinkedList<>();
        CiudadOD actual = destino;

        while (actual != null) {
            rutaOptima.add(0, actual);
            actual = padres.get(actual);
        }

        return rutaOptima;
    }

    private static class NodoDistancia implements Comparable<NodoDistancia> {
        private CiudadOD ciudad;
        private double distancia;

        public NodoDistancia(CiudadOD ciudad, double distancia) {
            this.ciudad = ciudad;
            this.distancia = distancia;
        }

        public CiudadOD getCiudad() {
            return ciudad;
        }

        public double getDistancia() {
            return distancia;
        }

        @Override
        public int compareTo(NodoDistancia otro) {
            return Double.compare(this.distancia, otro.distancia);
        }
    }
}
