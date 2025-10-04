package dominio.ABBNombre;

import dominio.Medicamento;
import dominio.RecorridosBusqueda;
import interfaz.Categoria;

public class ABBMedicamentoNombre {

    private NodoABBMedicamento raiz;

    public boolean insertar(Medicamento nuevo) {
        if (nuevo == null || nuevo.getNombre() == null) return false;

        if (raiz == null) {
            raiz = new NodoABBMedicamento(nuevo);
            return true;
        }

        return insertarRec(raiz, nuevo);
    }

    private boolean insertarRec(NodoABBMedicamento actual, Medicamento nuevo) {
        int cmp = nuevo.getNombre().compareToIgnoreCase(actual.getDato().getNombre());

        if (cmp == 0) return false;

        if (cmp < 0) {
            if (actual.getIzq() == null) {
                actual.setIzq(new NodoABBMedicamento(nuevo));
                return true;
            }
            return insertarRec(actual.getIzq(), nuevo);
        } else {
            if (actual.getDer() == null) {
                actual.setDer(new NodoABBMedicamento(nuevo));
                return true;
            }
            return insertarRec(actual.getDer(), nuevo);
        }
    }

    public String imprimirAsc() {
        StringBuilder sb = new StringBuilder();
        imprimirAscRec(raiz, sb);
        if (sb.length() > 0) sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    private void imprimirAscRec(NodoABBMedicamento nodo, StringBuilder sb) {
        if (nodo == null) return;

        imprimirAscRec(nodo.getIzq(), sb);

        Medicamento m = nodo.getDato();
        sb.append(m.getCodigo()).append(";")
                .append(m.getNombre()).append(";")
                .append(m.getFechaVencimiento()).append(";")
                .append(formatearCategoria(m.getCategoria()))
                .append("|");

        imprimirAscRec(nodo.getDer(), sb);
    }

    public String imprimirDes() {
        StringBuilder sb = new StringBuilder();
        imprimirDesRec(raiz, sb);
        if (sb.length() > 0) sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    private void imprimirDesRec(NodoABBMedicamento nodo, StringBuilder sb) {
        if (nodo == null) return;

        imprimirDesRec(nodo.getDer(), sb);

        Medicamento m = nodo.getDato();
        sb.append(m.getCodigo()).append(";")
                .append(m.getNombre()).append(";")
                .append(m.getFechaVencimiento()).append(";")
                .append(formatearCategoria(m.getCategoria()))
                .append("|");

        imprimirDesRec(nodo.getIzq(), sb);
    }

    private String formatearCategoria(Categoria c) {
        //Metodo auxiliar para imprimirAsc

        switch (c) {
            case VENTA_LIBRE: return "Venta libre";
            case RECETA_COMUN: return "Receta comun";
            case RECETA_CONTROLADA: return "Receta controlada";

            default: return c.toString();
        }
    }
}
