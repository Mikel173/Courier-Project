package datastructures;

import java.util.List;

import Model.Envio;
import util.Alerta;
import util.AlertasNotificaciones;

public class RegistroEnvios {
	private NodoArbol raiz;
    private EstadoEnvio estadoEnvio;
    private GrafoPonderado grafo;
    private AlertasNotificaciones alertasNotificaciones;

    public RegistroEnvios() {
        this.raiz = null;
        this.estadoEnvio = new EstadoEnvio();
        this.alertasNotificaciones = new AlertasNotificaciones();
    }

    private class NodoArbol {
        private Envio envio;
        private NodoArbol izquierda, derecha;

        public NodoArbol(Envio envio) {
            this.envio = envio;
            this.izquierda = null;
            this.derecha = null;
        }
    }
    private List<CiudadOD> obtenerRutaOptima(Envio envio) {
        CiudadOD origen = envio.getOrigen();
        CiudadOD destino = envio.getDestino();

        return AlgoritmoRutas.encontrarRutaOptima(grafo, origen, destino);
    }

    public void insertarEnvio(Envio envio) {
        CiudadOD origen = envio.getOrigen();
        CiudadOD destino = envio.getDestino();
        raiz = insertarRec(raiz, envio);
    }

    // Método auxiliar para la inserción recursiva en el árbol
    private NodoArbol insertarRec(NodoArbol nodo, Envio envio) {
        if (nodo == null) {
            return new NodoArbol(envio);
        }

        // Comparar por número de seguimiento para decidir la ubicación en el árbol
        int comparacion = envio.getNumeroSeguimiento().compareTo(nodo.envio.getNumeroSeguimiento());

        if (comparacion < 0) {
            nodo.izquierda = insertarRec(nodo.izquierda, envio);
        } else if (comparacion > 0) {
            nodo.derecha = insertarRec(nodo.derecha, envio);
        }

        return nodo;
    }

    public Envio buscarEnvio(String numeroSeguimiento) {
        return buscarRec(raiz, numeroSeguimiento);
    }

    private Envio buscarRec(NodoArbol nodo, String numeroSeguimiento) {
        if (nodo == null || nodo.envio.getNumeroSeguimiento().equals(numeroSeguimiento)) {
            return (nodo != null) ? nodo.envio : null;
        }

        int comparacion = numeroSeguimiento.compareTo(nodo.envio.getNumeroSeguimiento());

        if (comparacion < 0) {
            return buscarRec(nodo.izquierda, numeroSeguimiento);
        } else {
            return buscarRec(nodo.derecha, numeroSeguimiento);
        }
    }
    private int estimarTiempoDeEntrega(List<CiudadOD> rutaOptima) {

        int tiempoEstimado = 0;
        int tiempoPorCiudad = 100;

        for (CiudadOD ciudad : rutaOptima) {
            tiempoEstimado += tiempoPorCiudad;
        }

        return tiempoEstimado;
    }
    public void moverEnvioAutomaticamente(String numeroSeguimiento) {
        Envio envio = buscarEnvio(numeroSeguimiento);
        if (envio != null) {

        	estadoEnvio.agregarEstado(envio, EstadoEnvio.Estado.EN_TRANSITO);

            List<CiudadOD> rutaOptima = obtenerRutaOptima(envio);

            int tiempoEstimado = estimarTiempoDeEntrega(rutaOptima);

            long tiempoInicio = System.currentTimeMillis();


            for (CiudadOD ciudad : rutaOptima) {
                actualizarUbicacionEnArbol(envio, ciudad);

                simularTiempoDeTransito();
            }

            long tiempoFin = System.currentTimeMillis();

            long tiempoReal = tiempoFin - tiempoInicio;
            estadoEnvio.agregarEstado(envio, EstadoEnvio.Estado.ENTREGADO);

            mostrarHistorialCiudades(envio);

            System.out.println("Tiempo estimado: " + tiempoEstimado + " milisegundos");
            System.out.println("Tiempo real: " + tiempoReal + " milisegundos");
            
            manejarAlertas(envio, tiempoEstimado, tiempoReal);
        }
    }
    
    
    private void manejarAlertas(Envio envio, int tiempoEstimado, long tiempoReal) {
        if (tiempoReal > tiempoEstimado) {
            Alerta alertaRetraso = new Alerta("El envío se retrasó", 1);
            alertasNotificaciones.agregarAlerta(alertaRetraso);
        }

        if (envio.getUbicacionActual().equals(envio.getDestino())) {
            Alerta alertaEntregaExitosa = new Alerta("¡El envío ha sido entregado con éxito!", 0);
            alertasNotificaciones.agregarAlerta(alertaEntregaExitosa);
        }
    }

    
    

    private void mostrarHistorialCiudades(Envio envio) {
        System.out.println("Historial de ciudades por las que pasó el envío:");
        for (CiudadOD ciudad : envio.getHistorialCiudades()) {
            System.out.println(ciudad.getNombre());
        }
    }

    private void simularTiempoDeTransito() {
        try {
            Thread.sleep(500); // Simulación de 2 segundos
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
 /*
    private CiudadOD obtenerNuevaUbicacion() {
        // Reemplaza esto con la lógica para obtener la nueva ubicación
        // Puedes usar AlgoritmoRutas para calcular la próxima ciudad en la ruta óptima, por ejemplo
        return new CiudadOD("Nueva Ciudad");
    }
*/
    private void actualizarUbicacionEnArbol(Envio envio, CiudadOD nuevaUbicacion) {
        // Validar que nuevaUbicacion no sea null
        if (nuevaUbicacion != null) {
            envio.setUbicacionActual(nuevaUbicacion);
        } else {
            System.out.println("La nueva ubicación es inválida.");
        }
    }
}
