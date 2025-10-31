package dominio.GRAFO;

import dominio.COLA.Cola;
import dominio.Farmacia;
import dominio.ListaSE;
import dominio.NodoListaSE;

import java.util.List;

public class GrafoFarmacia {

    public int getMaxFarmacias() {
        return maxFarmacias;
    }

    public int getCantidad() {
        return cantidad;
    }

    private ListaSE<Farmacia> farmacias;
    private Conexion[][] matrizDeAdyacencias;
    private int maxFarmacias;
    private int cantidad;

    public GrafoFarmacia(int maxFarmacias){
        this.maxFarmacias = maxFarmacias;
        this.farmacias = new ListaSE<>();
        this.matrizDeAdyacencias = new Conexion[maxFarmacias][maxFarmacias];
        this.cantidad = 0;
    }

    public boolean existeFarmacia(String codigo) {
        NodoListaSE<Farmacia> aux = farmacias.getInicio();
        while (aux != null) {
            if (aux.getDato().getCodigo().equals(codigo)){
                return true;
            }
            aux = aux.getSig();
        }
        return false;
    }

    public void agregarFarmacia(Farmacia f) {
        farmacias.agregar(f);
        cantidad++;
    }

    public int getIndiceFarmacia(String codigo){
        NodoListaSE<Farmacia> aux = farmacias.getInicio();
        int i = 0;
        while (aux != null) {
            if (aux.getDato().getCodigo().equals(codigo)){
                return i;
            }
            aux = aux.getSig();
            i++;
        }
        return -1;
    }

    public Farmacia getFarmaciaPorIndice(int indice) {
        NodoListaSE<Farmacia> aux = farmacias.getInicio();
        int i = 0;
        while (aux != null) {
            if (i == indice) return aux.getDato();
            aux = aux.getSig();
            i++;
        }
        return null;
    }

    public Conexion getConexion(int i, int j){
        return matrizDeAdyacencias[i][j];
    }

    public boolean existeConexion(int i, int j){
        return matrizDeAdyacencias[i][j] != null;
    }

    public void crearConexion(int i, int j, Conexion conexion){
        matrizDeAdyacencias[i][j] = conexion;
        matrizDeAdyacencias[j][i] = conexion;
    }

    public ListaSE<Farmacia> obtenerRedFarmacias(String codigoOrigen, int cantidad) {
        int indiceOrigen = getIndiceFarmacia(codigoOrigen);

        boolean[] visitado = new boolean[maxFarmacias];
        int[] nivel = new int[maxFarmacias];
        Cola<Integer> cola = new Cola<>();

        visitado[indiceOrigen] = true;
        nivel[indiceOrigen] = 0;
        cola.encolar(indiceOrigen);

        ListaSE<Farmacia> farmaciasCercanas = new ListaSE<>();

        while (!cola.esVacia()) {
            int actual = cola.desencolar();

            for (int i = 0; i < maxFarmacias; i++) {
                Conexion conexion = getConexion(actual, i);

                if (conexion != null && !visitado[i]) {
                    nivel[i] = nivel[actual] + 1;

                    if (nivel[i] <= cantidad) {
                        visitado[i] = true;
                        cola.encolar(i);
                        Farmacia f = getFarmaciaPorIndice(i);
                        if (f != null) {
                            farmaciasCercanas.agregar(f);
                        }
                    }
                }
            }
        }

        return farmaciasCercanas;
    }





}
