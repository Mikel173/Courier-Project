package datastructures;


import Model.InformacionPaquete;

public class ArbolBinarioPaquetes {
    private NodoArbol raiz;

    private class NodoArbol {
        private InformacionPaquete informacionPaquete;
        private NodoArbol izquierda, derecha;

        public NodoArbol(InformacionPaquete informacionPaquete) {
            this.informacionPaquete = informacionPaquete;
            this.izquierda = null;
            this.derecha = null;
        }
    }

    public void insertarPaquete(InformacionPaquete informacionPaquete) {
        raiz = insertarRec(raiz, informacionPaquete);
    }

    private NodoArbol insertarRec(NodoArbol nodo, InformacionPaquete informacionPaquete) {
        if (nodo == null) {
            return new NodoArbol(informacionPaquete);
        }

        int comparacion = informacionPaquete.getId() - nodo.informacionPaquete.getId();

        if (comparacion < 0) {
            nodo.izquierda = insertarRec(nodo.izquierda, informacionPaquete);
        } else if (comparacion > 0) {
            nodo.derecha = insertarRec(nodo.derecha, informacionPaquete);
        }

        return nodo;
    }

    public InformacionPaquete buscarPaquete(int id) {
        return buscarRec(raiz, id);
    }

    private InformacionPaquete buscarRec(NodoArbol nodo, int id) {
        if (nodo == null || nodo.informacionPaquete.getId() == id) {
            return (nodo != null) ? nodo.informacionPaquete : null;
        }

        int comparacion = id - nodo.informacionPaquete.getId();

        if (comparacion < 0) {
            return buscarRec(nodo.izquierda, id);
        } else {
            return buscarRec(nodo.derecha, id);
        }
    }
}
