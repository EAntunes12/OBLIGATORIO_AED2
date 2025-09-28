package dominio.ABB;

import dominio.Medicamento;
import dominio.RecorridosBusqueda;
import interfaz.Categoria;

public class ABBMedicamento implements IABB{

    private NodoABBMedicamento raiz;

    public ABBMedicamento() {
        this.raiz = null;
    }

    public NodoABBMedicamento getRaiz() {
        return this.raiz;
    }

    @Override
    public boolean insertar(Medicamento nuevoMedicamento) {
        if (nuevoMedicamento == null || nuevoMedicamento.getCodigo() == null) {
            return false;
        }

        String codigo = nuevoMedicamento.getCodigo();

        if (this.raiz == null) {
            this.raiz = new NodoABBMedicamento(nuevoMedicamento);
            return true;
        } else {
            return insertarRecursivo(this.raiz, nuevoMedicamento, codigo);
        }
    }

    private boolean insertarRecursivo(NodoABBMedicamento actual, Medicamento nuevoMedicamento, String codigo) {

        int comparacion = codigo.compareTo(actual.getDato().getCodigo());

        if (comparacion == 0) {
            return false;
        }

        if (comparacion > 0) {
            if (actual.getDer() != null) {
                return insertarRecursivo(actual.getDer(), nuevoMedicamento, codigo);
            } else {
                actual.setDer(new NodoABBMedicamento(nuevoMedicamento));
                return true;
            }
        } else {
            if (actual.getIzq() != null) {
                return insertarRecursivo(actual.getIzq(), nuevoMedicamento, codigo);
            } else {
                actual.setIzq(new NodoABBMedicamento(nuevoMedicamento));
                return true;
            }
        }
    }

    @Override
    public boolean pertenece(String codigo) {
        return perteneceRecursivo(this.raiz, codigo);
    }

    private boolean perteneceRecursivo(NodoABBMedicamento nodo, String codigo) {
        if (nodo == null) {
            return false;
        }

        int comparacion = codigo.compareTo(nodo.getDato().getCodigo());

        if (comparacion == 0) {
            return true;
        }

        if (comparacion > 0) {
            return perteneceRecursivo(nodo.getDer(), codigo);
        }

        return perteneceRecursivo(nodo.getIzq(), codigo);
    }

    @Override
    public Medicamento obtener(String codigo) {
        return obtenerRecursivo(this.raiz, codigo);
    }

    private Medicamento obtenerRecursivo(NodoABBMedicamento nodo, String codigo) {
        if (nodo == null) {
            return null;
        }

        int comparacion = codigo.compareTo(nodo.getDato().getCodigo());

        if (comparacion == 0) {
            return nodo.getDato();
        }

        if (comparacion > 0) {
            return obtenerRecursivo(nodo.getDer(), codigo);
        }

        return obtenerRecursivo(nodo.getIzq(), codigo);
    }

    @Override
    public String imprimirAsc() {
        StringBuilder sb = new StringBuilder();
        imprimirAscRecursivo(this.raiz, sb);
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    private void imprimirAscRecursivo(NodoABBMedicamento nodo, StringBuilder sb) {
        if (nodo == null) return;

        imprimirAscRecursivo(nodo.getIzq(), sb);

        Medicamento m = nodo.getDato();
        sb.append(m.getCodigo()).append(";")
                .append(m.getNombre()).append(";")
                .append(m.getFechaVencimiento()).append(";")
                .append(formatearCategoria(m.getCategoria()))
                .append("|");

        imprimirAscRecursivo(nodo.getDer(), sb);
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

    @Override
    public void imprimirDes() {
        imprimirDesRecursivo(this.raiz);
        System.out.println("");
    }

    private void imprimirDesRecursivo(NodoABBMedicamento nodo) {
        if (nodo != null) {
            imprimirDesRecursivo(nodo.getDer());
            System.out.print(nodo.getDato().getCodigo() + " ");
            imprimirDesRecursivo(nodo.getIzq());
        }
    }

    @Override
    public Medicamento buscarPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return null;
        }
        return buscarPorNombreRecursivo(this.raiz, nombre.trim().toLowerCase());
    }

    private Medicamento buscarPorNombreRecursivo(NodoABBMedicamento nodo, String nombreBuscado) {
        if (nodo == null) {
            return null;
        }

        if (nodo.getDato().getNombre().toLowerCase().equals(nombreBuscado)) {
            return nodo.getDato();
        }

        Medicamento encontradoIzq = buscarPorNombreRecursivo(nodo.getIzq(), nombreBuscado);
        if (encontradoIzq != null) {
            return encontradoIzq;
        }

        return buscarPorNombreRecursivo(nodo.getDer(), nombreBuscado);
    }

    @Override
    public RecorridosBusqueda BuscarConContador(String codigo) {
        return BuscarConContadorRecursivo(this.raiz, codigo, 0);
    }

    private RecorridosBusqueda BuscarConContadorRecursivo(NodoABBMedicamento nodo, String codigo, int contador){
        if(nodo == null) {
            return new RecorridosBusqueda(null, contador);
        }

        contador++;
        int comparacion = codigo.compareTo(nodo.getDato().getCodigo());

        if (comparacion == 0) {
            return new RecorridosBusqueda(nodo.getDato(), contador);
        } else if (comparacion > 0) {
            return BuscarConContadorRecursivo(nodo.getDer(), codigo, contador);
        } else {
            return BuscarConContadorRecursivo(nodo.getIzq(), codigo, contador);
        }

    }
}