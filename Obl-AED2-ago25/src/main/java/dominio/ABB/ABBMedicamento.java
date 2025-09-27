package dominio.ABB;

import dominio.Medicamento;

public class ABBMedicamento implements IABB{

    private NodoABBMedicamento raiz;

    public ABBMedicamento() {
        this.raiz = null;
    }

    public NodoABBMedicamento getRaiz() {
        return this.raiz;
    }

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


    public void imprimirAsc() {
        imprimirAscRecursivo(this.raiz);
        System.out.println("");
    }

    private void imprimirAscRecursivo(NodoABBMedicamento nodo) {
        if (nodo != null) {
            imprimirAscRecursivo(nodo.getIzq());
            System.out.print(nodo.getDato().getCodigo() + " ");
            imprimirAscRecursivo(nodo.getDer());
        }
    }

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
}