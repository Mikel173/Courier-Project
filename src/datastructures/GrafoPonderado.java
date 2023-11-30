package datastructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GrafoPonderado {
    private Map<CiudadOD, List<Arista>> grafo;

    public GrafoPonderado() {
        this.grafo = new HashMap<>();
    }

    public void agregarNodo(CiudadOD nodo) {
        grafo.putIfAbsent(nodo, new LinkedList<>());
    }

    public void agregarArista(CiudadOD origen, CiudadOD destino, double peso) {
        Arista arista = new Arista(origen, destino, peso);
        grafo.computeIfAbsent(origen, k -> new ArrayList<>()).add(arista);
        // Agregar arista en sentido opuesto si el grafo no es dirigido
        grafo.computeIfAbsent(destino, k -> new ArrayList<>()).add(new Arista(destino, origen, peso));
    }

    public List<Arista> obtenerAristas(CiudadOD nodo) {
        return grafo.getOrDefault(nodo, new ArrayList<>());
    }

    public List<CiudadOD> obtenerCiudades() {
        return new ArrayList<>(grafo.keySet());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (CiudadOD nodo : grafo.keySet()) {
            sb.append(nodo).append(" -> ").append(grafo.get(nodo)).append("\n");
        }
        return sb.toString();
    }
}
