package datastructures;

import Model.Envio;

public class EstadoEnvio {
    private NodoEstado inicio;

    // Constructor
    public EstadoEnvio() {
        this.inicio = null;
    }

    private class NodoEstado {
        private Envio envio;
        private Estado estado;
        private NodoEstado siguiente;

        public NodoEstado(Envio envio, Estado estado) {
            this.envio = envio;
            this.estado = estado;
            this.siguiente = null;
        }
    }

    public enum Estado {
        EN_PROCESO,
        EN_TRANSITO,
        ENTREGADO
    }

    public void agregarEstado(Envio envio, Estado estado) {
        NodoEstado nuevoNodo = new NodoEstado(envio, estado);
        if (inicio == null) {
            inicio = nuevoNodo;
        } else {
            NodoEstado actual = inicio;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
    }

    public Estado obtenerEstadoActual(Envio envio) {
        NodoEstado actual = inicio;
        while (actual != null) {
            if (actual.envio.equals(envio)) {
                return actual.estado;
            }
            actual = actual.siguiente;
        }
        return null; // Envío no encontrado
    }
}
