package dominio.GRAFO;

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
    private boolean[][] matrizDeAdyacencias;
    private int maxFarmacias;
    private int cantidad;

    public GrafoFarmacia(int maxFarmacias){
        this.maxFarmacias = maxFarmacias;
        this.farmacias = new ListaSE<>();
        this.matrizDeAdyacencias = new boolean[maxFarmacias][maxFarmacias];
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


}
