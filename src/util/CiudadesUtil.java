package util;

import datastructures.CiudadOD;

import java.util.ArrayList;
import java.util.List;

public class CiudadesUtil {
    private static List<CiudadOD> ciudades = new ArrayList<>();

    static {
        ciudades.add(new CiudadOD("Buenos Aires"));
        ciudades.add(new CiudadOD("Córdoba"));
        ciudades.add(new CiudadOD("Rosario"));
        ciudades.add(new CiudadOD("Mendoza"));
    }

    public static List<CiudadOD> obtenerCiudades() {
        return new ArrayList<>(ciudades);
    }
}
