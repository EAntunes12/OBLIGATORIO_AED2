package dominio.ABB;

import dominio.ABB.NodoABBGenerico;
import dominio.Medicamento;
import dominio.RecorridosBusqueda;
import interfaz.Categoria;

public abstract class ABB<T> {

    private NodoABBGenerico<T> raiz;

    protected abstract Comparable getClave(T dato);

    protected String getNombre(T dato) {
        return null;
    }

    public ABB() {
        this.raiz = null;
    }

    public NodoABBGenerico<T> getRaiz() {
        return raiz;
    }

    public boolean insertar(T dato) {
        if (dato == null) return false;

        Comparable clave = getClave(dato);
        if (clave == null) return false;

        if (raiz == null) {
            raiz = new NodoABBGenerico<>(dato);
            return true;
        }
        return insertarRec(raiz, dato, clave);
    }

    private boolean insertarRec(NodoABBGenerico<T> nodo, T dato, Comparable clave) {
        Comparable claveNodo = getClave(nodo.getDato());
        int cmp = clave.compareTo(claveNodo);

        if (cmp == 0) return false; // duplicado

        if (cmp < 0) {
            if (nodo.getIzq() != null)
                return insertarRec(nodo.getIzq(), dato, clave);
            nodo.setIzq(new NodoABBGenerico<>(dato));
            return true;
        } else {
            if (nodo.getDer() != null)
                return insertarRec(nodo.getDer(), dato, clave);
            nodo.setDer(new NodoABBGenerico<>(dato));
            return true;
        }
    }

    public boolean pertenece(Comparable clave) {
        return perteneceRec(raiz, clave);
    }

    private boolean perteneceRec(NodoABBGenerico<T> nodo, Comparable clave) {
        if (nodo == null) return false;

        Comparable claveNodo = getClave(nodo.getDato());
        int cmp = clave.compareTo(claveNodo);

        if (cmp == 0) return true;
        if (cmp < 0) return perteneceRec(nodo.getIzq(), clave);
        return perteneceRec(nodo.getDer(), clave);
    }

    public T obtener(Comparable clave) {
        return obtenerRec(raiz, clave);
    }

    private T obtenerRec(NodoABBGenerico<T> nodo, Comparable clave) {
        if (nodo == null) return null;

        Comparable claveNodo = getClave(nodo.getDato());
        int cmp = clave.compareTo(claveNodo);

        if (cmp == 0) return nodo.getDato();
        if (cmp < 0) return obtenerRec(nodo.getIzq(), clave);
        return obtenerRec(nodo.getDer(), clave);
    }

    public String imprimirAsc() {
        StringBuilder sb = new StringBuilder();
        imprimirAscRec(raiz, sb);
        if (sb.length() > 0) sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    private void imprimirAscRec(NodoABBGenerico<T> nodo, StringBuilder sb) {
        if (nodo == null) return;
        imprimirAscRec(nodo.getIzq(), sb);
        sb.append(nodo.getDato().toString()).append("|");
        imprimirAscRec(nodo.getDer(), sb);
    }

    public String imprimirDes() {
        StringBuilder sb = new StringBuilder();
        imprimirDesRec(raiz, sb);
        if (sb.length() > 0) sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    private void imprimirDesRec(NodoABBGenerico<T> nodo, StringBuilder sb) {
        if (nodo == null) return;
        imprimirDesRec(nodo.getDer(), sb);
        sb.append(nodo.getDato().toString()).append("|");
        imprimirDesRec(nodo.getIzq(), sb);
    }

    public T buscarPorNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) return null;
        return buscarPorNombreRec(raiz, nombre.trim().toLowerCase());
    }

    private T buscarPorNombreRec(NodoABBGenerico<T> nodo, String nombre) {
        if (nodo == null) return null;

        String nombreNodo = getNombre(nodo.getDato());
        if (nombreNodo != null && nombreNodo.toLowerCase().equals(nombre)) {
            return nodo.getDato();
        }

        T izq = buscarPorNombreRec(nodo.getIzq(), nombre);
        if (izq != null) return izq;

        return buscarPorNombreRec(nodo.getDer(), nombre);
    }

    public RecorridosBusqueda buscarConContador(Comparable clave) {
        return buscarConContadorRec(raiz, clave, 0);
    }

    private RecorridosBusqueda buscarConContadorRec(NodoABBGenerico<T> nodo, Comparable clave, int cont) {
        if (nodo == null) return new RecorridosBusqueda(null, cont);

        Comparable claveNodo = getClave(nodo.getDato());
        cont++;
        int cmp = clave.compareTo(claveNodo);

        if (cmp == 0) return new RecorridosBusqueda((Medicamento) nodo.getDato(), cont);
        if (cmp < 0) return buscarConContadorRec(nodo.getIzq(), clave, cont);
        return buscarConContadorRec(nodo.getDer(), clave, cont);
    }

    public RecorridosBusqueda buscarPorNombreConContador(String nombre) {
        if (nombre == null || nombre.isEmpty()) return new RecorridosBusqueda(null, 0);
        return buscarPorNombreContRec(raiz, nombre.trim().toLowerCase(), 0);
    }

    private RecorridosBusqueda buscarPorNombreContRec(NodoABBGenerico<T> nodo, String nombre, int cont) {
        if (nodo == null) return new RecorridosBusqueda(null, cont);

        cont++;
        String nombreNodo = getNombre(nodo.getDato());
        if (nombreNodo != null && nombreNodo.toLowerCase().equals(nombre)) {
            return new RecorridosBusqueda((Medicamento) nodo.getDato(), cont);
        }

        RecorridosBusqueda izq = buscarPorNombreContRec(nodo.getIzq(), nombre, cont);
        if (izq.getMedicamento() != null) return izq;

        return buscarPorNombreContRec(nodo.getDer(), nombre, izq.getRecorridos());
    }

    public String imprimirPorCategoria(Categoria categoria) {
        StringBuilder sb = new StringBuilder();
        imprimirPorCategoriaRec(raiz, sb, categoria);
        if (sb.length() > 0) sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    private void imprimirPorCategoriaRec(NodoABBGenerico<T> nodo, StringBuilder sb, Categoria categoria) {
        if(nodo == null)  return;

        imprimirPorCategoriaRec(nodo.getIzq(), sb, categoria);
        if(((Medicamento) nodo.getDato()).getCategoria() == categoria){
            sb.append(nodo.getDato().toString()).append("|");
        }
        imprimirPorCategoriaRec(nodo.getDer(), sb, categoria);
    }

}
